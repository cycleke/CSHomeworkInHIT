/*
 * THIS FILE IS FOR IP TEST
 */
// system support
#include "sysInclude.h"

extern void ip_DiscardPkt(char *pBuffer, int type);

extern void ip_SendtoLower(char *pBuffer, int length);

extern void ip_SendtoUp(char *pBuffer, int length);

extern unsigned int getIpv4Address();

// implemented by students

#include <cstdlib>
#include <cstring>

int stud_ip_recv(char *pBuffer, unsigned short length) {
  unsigned short version = pBuffer[0] >> 4;      // 版本号
  unsigned short head_length = pBuffer[0] & 0xf; // 头部长度
  unsigned short ttl = pBuffer[8];               // 生存时间
  unsigned short checksum = ntohs(*(unsigned short *)(pBuffer + 10)); // 校验和
  unsigned int dest = ntohl(*(unsigned int *)(pBuffer + 16)); // 目的地址

  if (version != 4) {
    // IP 版本号错
    ip_DiscardPkt(pBuffer, STUD_IP_TEST_VERSION_ERROR);
    return 1;
  } else if (ttl <= 0) {
    // TTL 值出错
    ip_DiscardPkt(pBuffer, STUD_IP_TEST_TTL_ERROR);
    return 1;
  } else if (head_length < 5) {
    //头部长度错
    ip_DiscardPkt(pBuffer, STUD_IP_TEST_HEADLEN_ERROR);
    return 1;
  } else if (dest != getIpv4Address()) {
    //目的地址错
    ip_DiscardPkt(pBuffer, STUD_IP_TEST_DESTINATION_ERROR);
    return 1;
  }
  unsigned long sum = 0;
  for (int i = 0; i < head_length * 2; ++i) {
    sum += (unsigned char)pBuffer[i * 2] << 8;
    sum += (unsigned char)pBuffer[i * 2 + 1];
  }
  unsigned short l_word = sum & 0xffff;
  unsigned short h_word = sum >> 16;
  if (l_word + h_word != 0xffff) {
    // IP 校验和出错
    ip_DiscardPkt(pBuffer, STUD_IP_TEST_CHECKSUM_ERROR);
    return 1;
  }

  ip_SendtoUp(pBuffer, length);
  return 0;
}

int stud_ip_Upsend(char *pBuffer, unsigned short len, unsigned int srcAddr,
                   unsigned int dstAddr, byte protocol, byte ttl) {
  // 默认头部长度为 20 Bytes
  unsigned short ip_length = len + 20;

  char *buffer = (char *)malloc(ip_length * (sizeof(char)));
  memset(buffer, 0, ip_length * sizeof(char));
  buffer[0] = 0x45;     // 版本号和长度
  buffer[8] = ttl;      // 生存时间
  buffer[9] = protocol; // 协议

  // 转换为网络字节序
  unsigned short network_length = htons(ip_length);
  unsigned int src = htonl(srcAddr);
  unsigned int dest = htonl(dstAddr);
  memcpy(buffer + 2, &network_length, sizeof(unsigned short));
  memcpy(buffer + 12, &src, sizeof(unsigned int));
  memcpy(buffer + 16, &dest, sizeof(unsigned int));

  unsigned long sum = 0;
  for (int i = 0; i < 20; i += 2) {
    sum += (unsigned char)buffer[i] << 8;
    sum += (unsigned char)buffer[i + 1];
  }
  unsigned short l_word = sum & 0xffff;
  unsigned short h_word = sum >> 16;
  unsigned short checksum = l_word + h_word;
  checksum = ~checksum;

  unsigned short header_checksum = htons(checksum);
  memcpy(buffer + 10, &header_checksum, sizeof(unsigned short));
  memcpy(buffer + 20, pBuffer, len);
  // ip_SendtoLower(buffer, ip_length);
  ip_SendtoLower(buffer, len + 20);
  return 0;
}
