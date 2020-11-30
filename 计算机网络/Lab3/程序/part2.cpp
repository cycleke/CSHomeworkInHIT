/*
 * THIS FILE IS FOR IP FORWARD TEST
 */
#include "sysInclude.h"

// system support
extern void fwd_LocalRcv(char *pBuffer, int length);

extern void fwd_SendtoLower(char *pBuffer, int length, unsigned int nexthop);

extern void fwd_DiscardPkt(char *pBuffer, int type);

extern unsigned int getIpv4Address();

// implemented by students

#include <cstdlib>
#include <cstring>
#include <vector>

std::vector<stud_route_msg> routes;

void stud_Route_Init() {
  routes.clear(); // 清空路由
}

void stud_route_add(stud_route_msg *proute) {
  stud_route_msg temp;
  unsigned int dest = ntohl(proute->dest);
  unsigned int masklen = ntohl(proute->masklen);
  unsigned int nexthop = ntohl(proute->nexthop);
  temp.dest = dest;
  temp.masklen = masklen;
  temp.nexthop = nexthop;
  routes.push_back(temp);
}

int stud_fwd_deal(char *pBuffer, int length) {
  unsigned short version = pBuffer[0] >> 4;      // 版本号
  unsigned short head_length = pBuffer[0] & 0xf; // 头部长度
  unsigned short ttl = pBuffer[8];               // 生存时间
  unsigned short checksum = ntohs(*(unsigned short *)(pBuffer + 10)); // 校验和
  unsigned int dest = ntohl(*(unsigned int *)(pBuffer + 16)); // 目的地址

  if (ttl <= 0) {
    // TTL 值出错
    fwd_DiscardPkt(pBuffer, STUD_FORWARD_TEST_TTLERROR);
    return 1;
  } else if (dest == getIpv4Address()) {
    fwd_LocalRcv(pBuffer, length);
    return 0;
  }

  stud_route_msg *result = NULL;

  int routes_size = (int)routes.size();

  for (int i = 0; i < routes_size; ++i) {
    unsigned int sub_net =
        routes[i].dest & ((1 << 31) >> (routes[i].masklen - 1));
    if (sub_net == dest) {
      result = &routes[i];
      break;
    }
  }

  if (!result) {
    // 未找到对应的表
    fwd_DiscardPkt(pBuffer, STUD_FORWARD_TEST_NOROUTE);
    return 1;
  }

  char *buffer = (char *)malloc(length * sizeof(char));
  memcpy(buffer, pBuffer, length);
  buffer[8] = ttl - 1;
  memset(buffer + 10, 0, 2);

  unsigned long sum = 0;
  for (int i = 0; i < head_length * 2; ++i) {
    sum += (unsigned char)buffer[i * 2] << 8;
    sum += (unsigned char)buffer[i * 2 + 1];
  }
  unsigned short l_word = sum & 0xffff;
  unsigned short h_word = sum >> 16;
  unsigned short temp_checksum = l_word + h_word;
  temp_checksum = ~temp_checksum;
  unsigned short header_checksum = htons(temp_checksum);
  memcpy(buffer + 10, &header_checksum, 2);
  fwd_SendtoLower(buffer, length, result->nexthop);
  return 0;
}
