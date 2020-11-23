
hello：     文件格式 elf64-x86-64


Disassembly of section .init:

0000000000401000 <_init>:
  401000:	f3 0f 1e fa          	endbr64 
  401004:	48 83 ec 08          	sub    $0x8,%rsp
  401008:	48 8b 05 e9 2f 00 00 	mov    0x2fe9(%rip),%rax        # 403ff8 <__gmon_start__>
  40100f:	48 85 c0             	test   %rax,%rax
  401012:	74 02                	je     401016 <_init+0x16>
  401014:	ff d0                	callq  *%rax
  401016:	48 83 c4 08          	add    $0x8,%rsp
  40101a:	c3                   	retq   

Disassembly of section .plt:

0000000000401020 <.plt>:
  401020:	ff 35 e2 2f 00 00    	pushq  0x2fe2(%rip)        # 404008 <_GLOBAL_OFFSET_TABLE_+0x8>
  401026:	ff 25 e4 2f 00 00    	jmpq   *0x2fe4(%rip)        # 404010 <_GLOBAL_OFFSET_TABLE_+0x10>
  40102c:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000401030 <puts@plt>:
  401030:	ff 25 e2 2f 00 00    	jmpq   *0x2fe2(%rip)        # 404018 <puts@GLIBC_2.2.5>
  401036:	68 00 00 00 00       	pushq  $0x0
  40103b:	e9 e0 ff ff ff       	jmpq   401020 <.plt>

0000000000401040 <printf@plt>:
  401040:	ff 25 da 2f 00 00    	jmpq   *0x2fda(%rip)        # 404020 <printf@GLIBC_2.2.5>
  401046:	68 01 00 00 00       	pushq  $0x1
  40104b:	e9 d0 ff ff ff       	jmpq   401020 <.plt>

0000000000401050 <getchar@plt>:
  401050:	ff 25 d2 2f 00 00    	jmpq   *0x2fd2(%rip)        # 404028 <getchar@GLIBC_2.2.5>
  401056:	68 02 00 00 00       	pushq  $0x2
  40105b:	e9 c0 ff ff ff       	jmpq   401020 <.plt>

0000000000401060 <atoi@plt>:
  401060:	ff 25 ca 2f 00 00    	jmpq   *0x2fca(%rip)        # 404030 <atoi@GLIBC_2.2.5>
  401066:	68 03 00 00 00       	pushq  $0x3
  40106b:	e9 b0 ff ff ff       	jmpq   401020 <.plt>

0000000000401070 <exit@plt>:
  401070:	ff 25 c2 2f 00 00    	jmpq   *0x2fc2(%rip)        # 404038 <exit@GLIBC_2.2.5>
  401076:	68 04 00 00 00       	pushq  $0x4
  40107b:	e9 a0 ff ff ff       	jmpq   401020 <.plt>

0000000000401080 <sleep@plt>:
  401080:	ff 25 ba 2f 00 00    	jmpq   *0x2fba(%rip)        # 404040 <sleep@GLIBC_2.2.5>
  401086:	68 05 00 00 00       	pushq  $0x5
  40108b:	e9 90 ff ff ff       	jmpq   401020 <.plt>

Disassembly of section .text:

0000000000401090 <_start>:
  401090:	f3 0f 1e fa          	endbr64 
  401094:	31 ed                	xor    %ebp,%ebp
  401096:	49 89 d1             	mov    %rdx,%r9
  401099:	5e                   	pop    %rsi
  40109a:	48 89 e2             	mov    %rsp,%rdx
  40109d:	48 83 e4 f0          	and    $0xfffffffffffffff0,%rsp
  4010a1:	50                   	push   %rax
  4010a2:	54                   	push   %rsp
  4010a3:	49 c7 c0 40 11 40 00 	mov    $0x401140,%r8
  4010aa:	48 c7 c1 d0 10 40 00 	mov    $0x4010d0,%rcx
  4010b1:	48 c7 c7 45 11 40 00 	mov    $0x401145,%rdi
  4010b8:	ff 15 32 2f 00 00    	callq  *0x2f32(%rip)        # 403ff0 <__libc_start_main@GLIBC_2.2.5>
  4010be:	f4                   	hlt    
  4010bf:	90                   	nop

00000000004010c0 <_dl_relocate_static_pie>:
  4010c0:	f3 0f 1e fa          	endbr64 
  4010c4:	c3                   	retq   
  4010c5:	66 2e 0f 1f 84 00 00 	nopw   %cs:0x0(%rax,%rax,1)
  4010cc:	00 00 00 
  4010cf:	90                   	nop

00000000004010d0 <__libc_csu_init>:
  4010d0:	f3 0f 1e fa          	endbr64 
  4010d4:	41 57                	push   %r15
  4010d6:	4c 8d 3d 73 2d 00 00 	lea    0x2d73(%rip),%r15        # 403e50 <_DYNAMIC>
  4010dd:	41 56                	push   %r14
  4010df:	49 89 d6             	mov    %rdx,%r14
  4010e2:	41 55                	push   %r13
  4010e4:	49 89 f5             	mov    %rsi,%r13
  4010e7:	41 54                	push   %r12
  4010e9:	41 89 fc             	mov    %edi,%r12d
  4010ec:	55                   	push   %rbp
  4010ed:	48 8d 2d 5c 2d 00 00 	lea    0x2d5c(%rip),%rbp        # 403e50 <_DYNAMIC>
  4010f4:	53                   	push   %rbx
  4010f5:	4c 29 fd             	sub    %r15,%rbp
  4010f8:	48 83 ec 08          	sub    $0x8,%rsp
  4010fc:	e8 ff fe ff ff       	callq  401000 <_init>
  401101:	48 c1 fd 03          	sar    $0x3,%rbp
  401105:	74 1f                	je     401126 <__libc_csu_init+0x56>
  401107:	31 db                	xor    %ebx,%ebx
  401109:	0f 1f 80 00 00 00 00 	nopl   0x0(%rax)
  401110:	4c 89 f2             	mov    %r14,%rdx
  401113:	4c 89 ee             	mov    %r13,%rsi
  401116:	44 89 e7             	mov    %r12d,%edi
  401119:	41 ff 14 df          	callq  *(%r15,%rbx,8)
  40111d:	48 83 c3 01          	add    $0x1,%rbx
  401121:	48 39 dd             	cmp    %rbx,%rbp
  401124:	75 ea                	jne    401110 <__libc_csu_init+0x40>
  401126:	48 83 c4 08          	add    $0x8,%rsp
  40112a:	5b                   	pop    %rbx
  40112b:	5d                   	pop    %rbp
  40112c:	41 5c                	pop    %r12
  40112e:	41 5d                	pop    %r13
  401130:	41 5e                	pop    %r14
  401132:	41 5f                	pop    %r15
  401134:	c3                   	retq   
  401135:	66 66 2e 0f 1f 84 00 	data16 nopw %cs:0x0(%rax,%rax,1)
  40113c:	00 00 00 00 

0000000000401140 <__libc_csu_fini>:
  401140:	f3 0f 1e fa          	endbr64 
  401144:	c3                   	retq   

0000000000401145 <main>:
  401145:	55                   	push   %rbp
  401146:	48 89 e5             	mov    %rsp,%rbp
  401149:	48 83 ec 20          	sub    $0x20,%rsp
  40114d:	89 7d ec             	mov    %edi,-0x14(%rbp)
  401150:	48 89 75 e0          	mov    %rsi,-0x20(%rbp)
  401154:	83 7d ec 04          	cmpl   $0x4,-0x14(%rbp)
  401158:	74 16                	je     401170 <main+0x2b>
  40115a:	48 8d 3d a7 0e 00 00 	lea    0xea7(%rip),%rdi        # 402008 <_IO_stdin_used+0x8>
  401161:	e8 ca fe ff ff       	callq  401030 <puts@plt>
  401166:	bf 01 00 00 00       	mov    $0x1,%edi
  40116b:	e8 00 ff ff ff       	callq  401070 <exit@plt>
  401170:	c7 45 fc 00 00 00 00 	movl   $0x0,-0x4(%rbp)
  401177:	eb 48                	jmp    4011c1 <main+0x7c>
  401179:	48 8b 45 e0          	mov    -0x20(%rbp),%rax
  40117d:	48 83 c0 10          	add    $0x10,%rax
  401181:	48 8b 10             	mov    (%rax),%rdx
  401184:	48 8b 45 e0          	mov    -0x20(%rbp),%rax
  401188:	48 83 c0 08          	add    $0x8,%rax
  40118c:	48 8b 00             	mov    (%rax),%rax
  40118f:	48 89 c6             	mov    %rax,%rsi
  401192:	48 8d 3d 95 0e 00 00 	lea    0xe95(%rip),%rdi        # 40202e <_IO_stdin_used+0x2e>
  401199:	b8 00 00 00 00       	mov    $0x0,%eax
  40119e:	e8 9d fe ff ff       	callq  401040 <printf@plt>
  4011a3:	48 8b 45 e0          	mov    -0x20(%rbp),%rax
  4011a7:	48 83 c0 18          	add    $0x18,%rax
  4011ab:	48 8b 00             	mov    (%rax),%rax
  4011ae:	48 89 c7             	mov    %rax,%rdi
  4011b1:	e8 aa fe ff ff       	callq  401060 <atoi@plt>
  4011b6:	89 c7                	mov    %eax,%edi
  4011b8:	e8 c3 fe ff ff       	callq  401080 <sleep@plt>
  4011bd:	83 45 fc 01          	addl   $0x1,-0x4(%rbp)
  4011c1:	83 7d fc 07          	cmpl   $0x7,-0x4(%rbp)
  4011c5:	7e b2                	jle    401179 <main+0x34>
  4011c7:	e8 84 fe ff ff       	callq  401050 <getchar@plt>
  4011cc:	b8 00 00 00 00       	mov    $0x0,%eax
  4011d1:	c9                   	leaveq 
  4011d2:	c3                   	retq   

Disassembly of section .fini:

00000000004011d4 <_fini>:
  4011d4:	f3 0f 1e fa          	endbr64 
  4011d8:	48 83 ec 08          	sub    $0x8,%rsp
  4011dc:	48 83 c4 08          	add    $0x8,%rsp
  4011e0:	c3                   	retq   
