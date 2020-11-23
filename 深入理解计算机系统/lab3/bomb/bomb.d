
bomb：     文件格式 elf64-x86-64


Disassembly of section .init:

0000000000401000 <_init>:
  401000:	48 83 ec 08          	sub    $0x8,%rsp
  401004:	48 8b 05 ed 3f 00 00 	mov    0x3fed(%rip),%rax        # 404ff8 <__gmon_start__>
  40100b:	48 85 c0             	test   %rax,%rax
  40100e:	74 02                	je     401012 <_init+0x12>
  401010:	ff d0                	callq  *%rax
  401012:	48 83 c4 08          	add    $0x8,%rsp
  401016:	c3                   	retq   

Disassembly of section .plt:

0000000000401020 <.plt>:
  401020:	ff 35 e2 3f 00 00    	pushq  0x3fe2(%rip)        # 405008 <_GLOBAL_OFFSET_TABLE_+0x8>
  401026:	ff 25 e4 3f 00 00    	jmpq   *0x3fe4(%rip)        # 405010 <_GLOBAL_OFFSET_TABLE_+0x10>
  40102c:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000401030 <getenv@plt>:
  401030:	ff 25 e2 3f 00 00    	jmpq   *0x3fe2(%rip)        # 405018 <getenv@GLIBC_2.2.5>
  401036:	68 00 00 00 00       	pushq  $0x0
  40103b:	e9 e0 ff ff ff       	jmpq   401020 <.plt>

0000000000401040 <__errno_location@plt>:
  401040:	ff 25 da 3f 00 00    	jmpq   *0x3fda(%rip)        # 405020 <__errno_location@GLIBC_2.2.5>
  401046:	68 01 00 00 00       	pushq  $0x1
  40104b:	e9 d0 ff ff ff       	jmpq   401020 <.plt>

0000000000401050 <strcpy@plt>:
  401050:	ff 25 d2 3f 00 00    	jmpq   *0x3fd2(%rip)        # 405028 <strcpy@GLIBC_2.2.5>
  401056:	68 02 00 00 00       	pushq  $0x2
  40105b:	e9 c0 ff ff ff       	jmpq   401020 <.plt>

0000000000401060 <puts@plt>:
  401060:	ff 25 ca 3f 00 00    	jmpq   *0x3fca(%rip)        # 405030 <puts@GLIBC_2.2.5>
  401066:	68 03 00 00 00       	pushq  $0x3
  40106b:	e9 b0 ff ff ff       	jmpq   401020 <.plt>

0000000000401070 <write@plt>:
  401070:	ff 25 c2 3f 00 00    	jmpq   *0x3fc2(%rip)        # 405038 <write@GLIBC_2.2.5>
  401076:	68 04 00 00 00       	pushq  $0x4
  40107b:	e9 a0 ff ff ff       	jmpq   401020 <.plt>

0000000000401080 <alarm@plt>:
  401080:	ff 25 ba 3f 00 00    	jmpq   *0x3fba(%rip)        # 405040 <alarm@GLIBC_2.2.5>
  401086:	68 05 00 00 00       	pushq  $0x5
  40108b:	e9 90 ff ff ff       	jmpq   401020 <.plt>

0000000000401090 <close@plt>:
  401090:	ff 25 b2 3f 00 00    	jmpq   *0x3fb2(%rip)        # 405048 <close@GLIBC_2.2.5>
  401096:	68 06 00 00 00       	pushq  $0x6
  40109b:	e9 80 ff ff ff       	jmpq   401020 <.plt>

00000000004010a0 <read@plt>:
  4010a0:	ff 25 aa 3f 00 00    	jmpq   *0x3faa(%rip)        # 405050 <read@GLIBC_2.2.5>
  4010a6:	68 07 00 00 00       	pushq  $0x7
  4010ab:	e9 70 ff ff ff       	jmpq   401020 <.plt>

00000000004010b0 <fgets@plt>:
  4010b0:	ff 25 a2 3f 00 00    	jmpq   *0x3fa2(%rip)        # 405058 <fgets@GLIBC_2.2.5>
  4010b6:	68 08 00 00 00       	pushq  $0x8
  4010bb:	e9 60 ff ff ff       	jmpq   401020 <.plt>

00000000004010c0 <signal@plt>:
  4010c0:	ff 25 9a 3f 00 00    	jmpq   *0x3f9a(%rip)        # 405060 <signal@GLIBC_2.2.5>
  4010c6:	68 09 00 00 00       	pushq  $0x9
  4010cb:	e9 50 ff ff ff       	jmpq   401020 <.plt>

00000000004010d0 <gethostbyname@plt>:
  4010d0:	ff 25 92 3f 00 00    	jmpq   *0x3f92(%rip)        # 405068 <gethostbyname@GLIBC_2.2.5>
  4010d6:	68 0a 00 00 00       	pushq  $0xa
  4010db:	e9 40 ff ff ff       	jmpq   401020 <.plt>

00000000004010e0 <__memmove_chk@plt>:
  4010e0:	ff 25 8a 3f 00 00    	jmpq   *0x3f8a(%rip)        # 405070 <__memmove_chk@GLIBC_2.3.4>
  4010e6:	68 0b 00 00 00       	pushq  $0xb
  4010eb:	e9 30 ff ff ff       	jmpq   401020 <.plt>

00000000004010f0 <strtol@plt>:
  4010f0:	ff 25 82 3f 00 00    	jmpq   *0x3f82(%rip)        # 405078 <strtol@GLIBC_2.2.5>
  4010f6:	68 0c 00 00 00       	pushq  $0xc
  4010fb:	e9 20 ff ff ff       	jmpq   401020 <.plt>

0000000000401100 <memcpy@plt>:
  401100:	ff 25 7a 3f 00 00    	jmpq   *0x3f7a(%rip)        # 405080 <memcpy@GLIBC_2.14>
  401106:	68 0d 00 00 00       	pushq  $0xd
  40110b:	e9 10 ff ff ff       	jmpq   401020 <.plt>

0000000000401110 <fflush@plt>:
  401110:	ff 25 72 3f 00 00    	jmpq   *0x3f72(%rip)        # 405088 <fflush@GLIBC_2.2.5>
  401116:	68 0e 00 00 00       	pushq  $0xe
  40111b:	e9 00 ff ff ff       	jmpq   401020 <.plt>

0000000000401120 <__isoc99_sscanf@plt>:
  401120:	ff 25 6a 3f 00 00    	jmpq   *0x3f6a(%rip)        # 405090 <__isoc99_sscanf@GLIBC_2.7>
  401126:	68 0f 00 00 00       	pushq  $0xf
  40112b:	e9 f0 fe ff ff       	jmpq   401020 <.plt>

0000000000401130 <__printf_chk@plt>:
  401130:	ff 25 62 3f 00 00    	jmpq   *0x3f62(%rip)        # 405098 <__printf_chk@GLIBC_2.3.4>
  401136:	68 10 00 00 00       	pushq  $0x10
  40113b:	e9 e0 fe ff ff       	jmpq   401020 <.plt>

0000000000401140 <fopen@plt>:
  401140:	ff 25 5a 3f 00 00    	jmpq   *0x3f5a(%rip)        # 4050a0 <fopen@GLIBC_2.2.5>
  401146:	68 11 00 00 00       	pushq  $0x11
  40114b:	e9 d0 fe ff ff       	jmpq   401020 <.plt>

0000000000401150 <exit@plt>:
  401150:	ff 25 52 3f 00 00    	jmpq   *0x3f52(%rip)        # 4050a8 <exit@GLIBC_2.2.5>
  401156:	68 12 00 00 00       	pushq  $0x12
  40115b:	e9 c0 fe ff ff       	jmpq   401020 <.plt>

0000000000401160 <connect@plt>:
  401160:	ff 25 4a 3f 00 00    	jmpq   *0x3f4a(%rip)        # 4050b0 <connect@GLIBC_2.2.5>
  401166:	68 13 00 00 00       	pushq  $0x13
  40116b:	e9 b0 fe ff ff       	jmpq   401020 <.plt>

0000000000401170 <__fprintf_chk@plt>:
  401170:	ff 25 42 3f 00 00    	jmpq   *0x3f42(%rip)        # 4050b8 <__fprintf_chk@GLIBC_2.3.4>
  401176:	68 14 00 00 00       	pushq  $0x14
  40117b:	e9 a0 fe ff ff       	jmpq   401020 <.plt>

0000000000401180 <sleep@plt>:
  401180:	ff 25 3a 3f 00 00    	jmpq   *0x3f3a(%rip)        # 4050c0 <sleep@GLIBC_2.2.5>
  401186:	68 15 00 00 00       	pushq  $0x15
  40118b:	e9 90 fe ff ff       	jmpq   401020 <.plt>

0000000000401190 <__ctype_b_loc@plt>:
  401190:	ff 25 32 3f 00 00    	jmpq   *0x3f32(%rip)        # 4050c8 <__ctype_b_loc@GLIBC_2.3>
  401196:	68 16 00 00 00       	pushq  $0x16
  40119b:	e9 80 fe ff ff       	jmpq   401020 <.plt>

00000000004011a0 <__sprintf_chk@plt>:
  4011a0:	ff 25 2a 3f 00 00    	jmpq   *0x3f2a(%rip)        # 4050d0 <__sprintf_chk@GLIBC_2.3.4>
  4011a6:	68 17 00 00 00       	pushq  $0x17
  4011ab:	e9 70 fe ff ff       	jmpq   401020 <.plt>

00000000004011b0 <socket@plt>:
  4011b0:	ff 25 22 3f 00 00    	jmpq   *0x3f22(%rip)        # 4050d8 <socket@GLIBC_2.2.5>
  4011b6:	68 18 00 00 00       	pushq  $0x18
  4011bb:	e9 60 fe ff ff       	jmpq   401020 <.plt>

Disassembly of section .text:

00000000004011c0 <_start>:
  4011c0:	31 ed                	xor    %ebp,%ebp
  4011c2:	49 89 d1             	mov    %rdx,%r9
  4011c5:	5e                   	pop    %rsi
  4011c6:	48 89 e2             	mov    %rsp,%rdx
  4011c9:	48 83 e4 f0          	and    $0xfffffffffffffff0,%rsp
  4011cd:	50                   	push   %rax
  4011ce:	54                   	push   %rsp
  4011cf:	49 c7 c0 c0 27 40 00 	mov    $0x4027c0,%r8
  4011d6:	48 c7 c1 60 27 40 00 	mov    $0x402760,%rcx
  4011dd:	48 c7 c7 a2 12 40 00 	mov    $0x4012a2,%rdi
  4011e4:	ff 15 06 3e 00 00    	callq  *0x3e06(%rip)        # 404ff0 <__libc_start_main@GLIBC_2.2.5>
  4011ea:	f4                   	hlt    
  4011eb:	0f 1f 44 00 00       	nopl   0x0(%rax,%rax,1)

00000000004011f0 <_dl_relocate_static_pie>:
  4011f0:	c3                   	retq   
  4011f1:	66 2e 0f 1f 84 00 00 	nopw   %cs:0x0(%rax,%rax,1)
  4011f8:	00 00 00 
  4011fb:	0f 1f 44 00 00       	nopl   0x0(%rax,%rax,1)

0000000000401200 <deregister_tm_clones>:
  401200:	b8 40 57 40 00       	mov    $0x405740,%eax
  401205:	48 3d 40 57 40 00    	cmp    $0x405740,%rax
  40120b:	74 13                	je     401220 <deregister_tm_clones+0x20>
  40120d:	b8 00 00 00 00       	mov    $0x0,%eax
  401212:	48 85 c0             	test   %rax,%rax
  401215:	74 09                	je     401220 <deregister_tm_clones+0x20>
  401217:	bf 40 57 40 00       	mov    $0x405740,%edi
  40121c:	ff e0                	jmpq   *%rax
  40121e:	66 90                	xchg   %ax,%ax
  401220:	c3                   	retq   
  401221:	66 66 2e 0f 1f 84 00 	data16 nopw %cs:0x0(%rax,%rax,1)
  401228:	00 00 00 00 
  40122c:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000401230 <register_tm_clones>:
  401230:	be 40 57 40 00       	mov    $0x405740,%esi
  401235:	48 81 ee 40 57 40 00 	sub    $0x405740,%rsi
  40123c:	48 c1 fe 03          	sar    $0x3,%rsi
  401240:	48 89 f0             	mov    %rsi,%rax
  401243:	48 c1 e8 3f          	shr    $0x3f,%rax
  401247:	48 01 c6             	add    %rax,%rsi
  40124a:	48 d1 fe             	sar    %rsi
  40124d:	74 11                	je     401260 <register_tm_clones+0x30>
  40124f:	b8 00 00 00 00       	mov    $0x0,%eax
  401254:	48 85 c0             	test   %rax,%rax
  401257:	74 07                	je     401260 <register_tm_clones+0x30>
  401259:	bf 40 57 40 00       	mov    $0x405740,%edi
  40125e:	ff e0                	jmpq   *%rax
  401260:	c3                   	retq   
  401261:	66 66 2e 0f 1f 84 00 	data16 nopw %cs:0x0(%rax,%rax,1)
  401268:	00 00 00 00 
  40126c:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000401270 <__do_global_dtors_aux>:
  401270:	80 3d f1 44 00 00 00 	cmpb   $0x0,0x44f1(%rip)        # 405768 <completed.7963>
  401277:	75 17                	jne    401290 <__do_global_dtors_aux+0x20>
  401279:	55                   	push   %rbp
  40127a:	48 89 e5             	mov    %rsp,%rbp
  40127d:	e8 7e ff ff ff       	callq  401200 <deregister_tm_clones>
  401282:	c6 05 df 44 00 00 01 	movb   $0x1,0x44df(%rip)        # 405768 <completed.7963>
  401289:	5d                   	pop    %rbp
  40128a:	c3                   	retq   
  40128b:	0f 1f 44 00 00       	nopl   0x0(%rax,%rax,1)
  401290:	c3                   	retq   
  401291:	66 66 2e 0f 1f 84 00 	data16 nopw %cs:0x0(%rax,%rax,1)
  401298:	00 00 00 00 
  40129c:	0f 1f 40 00          	nopl   0x0(%rax)

00000000004012a0 <frame_dummy>:
  4012a0:	eb 8e                	jmp    401230 <register_tm_clones>

00000000004012a2 <main>:
  4012a2:	55                   	push   %rbp
  4012a3:	48 89 e5             	mov    %rsp,%rbp
  4012a6:	53                   	push   %rbx
  4012a7:	48 83 ec 08          	sub    $0x8,%rsp
  4012ab:	83 ff 01             	cmp    $0x1,%edi
  4012ae:	0f 84 ed 00 00 00    	je     4013a1 <main+0xff>
  4012b4:	48 89 f3             	mov    %rsi,%rbx
  4012b7:	83 ff 02             	cmp    $0x2,%edi
  4012ba:	0f 85 14 01 00 00    	jne    4013d4 <main+0x132>
  4012c0:	48 8b 7e 08          	mov    0x8(%rsi),%rdi
  4012c4:	be 04 30 40 00       	mov    $0x403004,%esi
  4012c9:	e8 72 fe ff ff       	callq  401140 <fopen@plt>
  4012ce:	48 89 05 9b 44 00 00 	mov    %rax,0x449b(%rip)        # 405770 <infile>
  4012d5:	48 85 c0             	test   %rax,%rax
  4012d8:	0f 84 d6 00 00 00    	je     4013b4 <main+0x112>
  4012de:	e8 81 05 00 00       	callq  401864 <initialize_bomb>
  4012e3:	bf 88 30 40 00       	mov    $0x403088,%edi
  4012e8:	e8 73 fd ff ff       	callq  401060 <puts@plt>
  4012ed:	bf c8 30 40 00       	mov    $0x4030c8,%edi
  4012f2:	e8 69 fd ff ff       	callq  401060 <puts@plt>
  4012f7:	e8 68 06 00 00       	callq  401964 <read_line>
  4012fc:	48 89 c7             	mov    %rax,%rdi
  4012ff:	e8 f1 00 00 00       	callq  4013f5 <phase_1>
  401304:	e8 8c 07 00 00       	callq  401a95 <phase_defused>
  401309:	bf f8 30 40 00       	mov    $0x4030f8,%edi
  40130e:	e8 4d fd ff ff       	callq  401060 <puts@plt>
  401313:	e8 4c 06 00 00       	callq  401964 <read_line>
  401318:	48 89 c7             	mov    %rax,%rdi
  40131b:	e8 f0 00 00 00       	callq  401410 <phase_2>
  401320:	e8 70 07 00 00       	callq  401a95 <phase_defused>
  401325:	bf 3d 30 40 00       	mov    $0x40303d,%edi
  40132a:	e8 31 fd ff ff       	callq  401060 <puts@plt>
  40132f:	e8 30 06 00 00       	callq  401964 <read_line>
  401334:	48 89 c7             	mov    %rax,%rdi
  401337:	e8 30 01 00 00       	callq  40146c <phase_3>
  40133c:	e8 54 07 00 00       	callq  401a95 <phase_defused>
  401341:	bf 5b 30 40 00       	mov    $0x40305b,%edi
  401346:	e8 15 fd ff ff       	callq  401060 <puts@plt>
  40134b:	e8 14 06 00 00       	callq  401964 <read_line>
  401350:	48 89 c7             	mov    %rax,%rdi
  401353:	e8 e9 01 00 00       	callq  401541 <phase_4>
  401358:	e8 38 07 00 00       	callq  401a95 <phase_defused>
  40135d:	bf 28 31 40 00       	mov    $0x403128,%edi
  401362:	e8 f9 fc ff ff       	callq  401060 <puts@plt>
  401367:	e8 f8 05 00 00       	callq  401964 <read_line>
  40136c:	48 89 c7             	mov    %rax,%rdi
  40136f:	e8 1e 02 00 00       	callq  401592 <phase_5>
  401374:	e8 1c 07 00 00       	callq  401a95 <phase_defused>
  401379:	bf 6a 30 40 00       	mov    $0x40306a,%edi
  40137e:	e8 dd fc ff ff       	callq  401060 <puts@plt>
  401383:	e8 dc 05 00 00       	callq  401964 <read_line>
  401388:	48 89 c7             	mov    %rax,%rdi
  40138b:	e8 57 02 00 00       	callq  4015e7 <phase_6>
  401390:	e8 00 07 00 00       	callq  401a95 <phase_defused>
  401395:	b8 00 00 00 00       	mov    $0x0,%eax
  40139a:	48 83 c4 08          	add    $0x8,%rsp
  40139e:	5b                   	pop    %rbx
  40139f:	5d                   	pop    %rbp
  4013a0:	c3                   	retq   
  4013a1:	48 8b 05 a8 43 00 00 	mov    0x43a8(%rip),%rax        # 405750 <stdin@@GLIBC_2.2.5>
  4013a8:	48 89 05 c1 43 00 00 	mov    %rax,0x43c1(%rip)        # 405770 <infile>
  4013af:	e9 2a ff ff ff       	jmpq   4012de <main+0x3c>
  4013b4:	48 8b 4b 08          	mov    0x8(%rbx),%rcx
  4013b8:	48 8b 13             	mov    (%rbx),%rdx
  4013bb:	be 06 30 40 00       	mov    $0x403006,%esi
  4013c0:	bf 01 00 00 00       	mov    $0x1,%edi
  4013c5:	e8 66 fd ff ff       	callq  401130 <__printf_chk@plt>
  4013ca:	bf 08 00 00 00       	mov    $0x8,%edi
  4013cf:	e8 7c fd ff ff       	callq  401150 <exit@plt>
  4013d4:	48 8b 16             	mov    (%rsi),%rdx
  4013d7:	be 23 30 40 00       	mov    $0x403023,%esi
  4013dc:	bf 01 00 00 00       	mov    $0x1,%edi
  4013e1:	b8 00 00 00 00       	mov    $0x0,%eax
  4013e6:	e8 45 fd ff ff       	callq  401130 <__printf_chk@plt>
  4013eb:	bf 08 00 00 00       	mov    $0x8,%edi
  4013f0:	e8 5b fd ff ff       	callq  401150 <exit@plt>

00000000004013f5 <phase_1>:
  4013f5:	55                   	push   %rbp
  4013f6:	48 89 e5             	mov    %rsp,%rbp
  4013f9:	be 50 31 40 00       	mov    $0x403150,%esi
  4013fe:	e8 04 04 00 00       	callq  401807 <strings_not_equal>
  401403:	85 c0                	test   %eax,%eax
  401405:	75 02                	jne    401409 <phase_1+0x14>
  401407:	5d                   	pop    %rbp
  401408:	c3                   	retq   
  401409:	e8 f8 04 00 00       	callq  401906 <explode_bomb>
  40140e:	eb f7                	jmp    401407 <phase_1+0x12>

0000000000401410 <phase_2>:
  401410:	55                   	push   %rbp
  401411:	48 89 e5             	mov    %rsp,%rbp
  401414:	53                   	push   %rbx
  401415:	48 83 ec 28          	sub    $0x28,%rsp
  401419:	48 8d 75 d0          	lea    -0x30(%rbp),%rsi
  40141d:	e8 06 05 00 00       	callq  401928 <read_six_numbers>
  401422:	83 7d d0 00          	cmpl   $0x0,-0x30(%rbp)
  401426:	75 06                	jne    40142e <phase_2+0x1e>
  401428:	83 7d d4 01          	cmpl   $0x1,-0x2c(%rbp)
  40142c:	74 05                	je     401433 <phase_2+0x23>
  40142e:	e8 d3 04 00 00       	callq  401906 <explode_bomb>
  401433:	bb 02 00 00 00       	mov    $0x2,%ebx
  401438:	eb 08                	jmp    401442 <phase_2+0x32>
  40143a:	e8 c7 04 00 00       	callq  401906 <explode_bomb>
  40143f:	83 c3 01             	add    $0x1,%ebx
  401442:	83 fb 05             	cmp    $0x5,%ebx
  401445:	7f 1e                	jg     401465 <phase_2+0x55>
  401447:	48 63 d3             	movslq %ebx,%rdx
  40144a:	8d 4b fe             	lea    -0x2(%rbx),%ecx
  40144d:	48 63 c9             	movslq %ecx,%rcx
  401450:	8d 43 ff             	lea    -0x1(%rbx),%eax
  401453:	48 98                	cltq   
  401455:	8b 44 85 d0          	mov    -0x30(%rbp,%rax,4),%eax
  401459:	03 44 8d d0          	add    -0x30(%rbp,%rcx,4),%eax
  40145d:	39 44 95 d0          	cmp    %eax,-0x30(%rbp,%rdx,4)
  401461:	74 dc                	je     40143f <phase_2+0x2f>
  401463:	eb d5                	jmp    40143a <phase_2+0x2a>
  401465:	48 83 c4 28          	add    $0x28,%rsp
  401469:	5b                   	pop    %rbx
  40146a:	5d                   	pop    %rbp
  40146b:	c3                   	retq   

000000000040146c <phase_3>:
  40146c:	55                   	push   %rbp
  40146d:	48 89 e5             	mov    %rsp,%rbp
  401470:	48 83 ec 10          	sub    $0x10,%rsp
  401474:	48 8d 4d f8          	lea    -0x8(%rbp),%rcx
  401478:	48 8d 55 fc          	lea    -0x4(%rbp),%rdx
  40147c:	be 4f 33 40 00       	mov    $0x40334f,%esi
  401481:	b8 00 00 00 00       	mov    $0x0,%eax
  401486:	e8 95 fc ff ff       	callq  401120 <__isoc99_sscanf@plt>
  40148b:	83 f8 01             	cmp    $0x1,%eax
  40148e:	7e 10                	jle    4014a0 <phase_3+0x34>
  401490:	83 7d fc 07          	cmpl   $0x7,-0x4(%rbp)
  401494:	77 42                	ja     4014d8 <phase_3+0x6c>
  401496:	8b 45 fc             	mov    -0x4(%rbp),%eax
  401499:	ff 24 c5 c0 31 40 00 	jmpq   *0x4031c0(,%rax,8)
  4014a0:	e8 61 04 00 00       	callq  401906 <explode_bomb>
  4014a5:	eb e9                	jmp    401490 <phase_3+0x24>
  4014a7:	b8 6c 00 00 00       	mov    $0x6c,%eax
  4014ac:	eb 3b                	jmp    4014e9 <phase_3+0x7d>
  4014ae:	b8 c5 00 00 00       	mov    $0xc5,%eax
  4014b3:	eb 34                	jmp    4014e9 <phase_3+0x7d>
  4014b5:	b8 22 01 00 00       	mov    $0x122,%eax
  4014ba:	eb 2d                	jmp    4014e9 <phase_3+0x7d>
  4014bc:	b8 aa 03 00 00       	mov    $0x3aa,%eax
  4014c1:	eb 26                	jmp    4014e9 <phase_3+0x7d>
  4014c3:	b8 38 00 00 00       	mov    $0x38,%eax
  4014c8:	eb 1f                	jmp    4014e9 <phase_3+0x7d>
  4014ca:	b8 85 02 00 00       	mov    $0x285,%eax
  4014cf:	eb 18                	jmp    4014e9 <phase_3+0x7d>
  4014d1:	b8 c0 00 00 00       	mov    $0xc0,%eax
  4014d6:	eb 11                	jmp    4014e9 <phase_3+0x7d>
  4014d8:	e8 29 04 00 00       	callq  401906 <explode_bomb>
  4014dd:	b8 00 00 00 00       	mov    $0x0,%eax
  4014e2:	eb 05                	jmp    4014e9 <phase_3+0x7d>
  4014e4:	b8 a9 00 00 00       	mov    $0xa9,%eax
  4014e9:	39 45 f8             	cmp    %eax,-0x8(%rbp)
  4014ec:	75 02                	jne    4014f0 <phase_3+0x84>
  4014ee:	c9                   	leaveq 
  4014ef:	c3                   	retq   
  4014f0:	e8 11 04 00 00       	callq  401906 <explode_bomb>
  4014f5:	eb f7                	jmp    4014ee <phase_3+0x82>

00000000004014f7 <func4>:
  4014f7:	85 ff                	test   %edi,%edi
  4014f9:	7e 3d                	jle    401538 <func4+0x41>
  4014fb:	83 ff 01             	cmp    $0x1,%edi
  4014fe:	74 3e                	je     40153e <func4+0x47>
  401500:	55                   	push   %rbp
  401501:	48 89 e5             	mov    %rsp,%rbp
  401504:	41 55                	push   %r13
  401506:	41 54                	push   %r12
  401508:	53                   	push   %rbx
  401509:	48 83 ec 08          	sub    $0x8,%rsp
  40150d:	89 f3                	mov    %esi,%ebx
  40150f:	41 89 fc             	mov    %edi,%r12d
  401512:	8d 7f ff             	lea    -0x1(%rdi),%edi
  401515:	e8 dd ff ff ff       	callq  4014f7 <func4>
  40151a:	44 8d 2c 18          	lea    (%rax,%rbx,1),%r13d
  40151e:	41 8d 7c 24 fe       	lea    -0x2(%r12),%edi
  401523:	89 de                	mov    %ebx,%esi
  401525:	e8 cd ff ff ff       	callq  4014f7 <func4>
  40152a:	44 01 e8             	add    %r13d,%eax
  40152d:	48 83 c4 08          	add    $0x8,%rsp
  401531:	5b                   	pop    %rbx
  401532:	41 5c                	pop    %r12
  401534:	41 5d                	pop    %r13
  401536:	5d                   	pop    %rbp
  401537:	c3                   	retq   
  401538:	b8 00 00 00 00       	mov    $0x0,%eax
  40153d:	c3                   	retq   
  40153e:	89 f0                	mov    %esi,%eax
  401540:	c3                   	retq   

0000000000401541 <phase_4>:
  401541:	55                   	push   %rbp
  401542:	48 89 e5             	mov    %rsp,%rbp
  401545:	48 83 ec 10          	sub    $0x10,%rsp
  401549:	48 8d 4d fc          	lea    -0x4(%rbp),%rcx
  40154d:	48 8d 55 f8          	lea    -0x8(%rbp),%rdx
  401551:	be 4f 33 40 00       	mov    $0x40334f,%esi
  401556:	b8 00 00 00 00       	mov    $0x0,%eax
  40155b:	e8 c0 fb ff ff       	callq  401120 <__isoc99_sscanf@plt>
  401560:	83 f8 02             	cmp    $0x2,%eax
  401563:	75 0d                	jne    401572 <phase_4+0x31>
  401565:	8b 45 fc             	mov    -0x4(%rbp),%eax
  401568:	83 f8 01             	cmp    $0x1,%eax
  40156b:	7e 05                	jle    401572 <phase_4+0x31>
  40156d:	83 f8 04             	cmp    $0x4,%eax
  401570:	7e 05                	jle    401577 <phase_4+0x36>
  401572:	e8 8f 03 00 00       	callq  401906 <explode_bomb>
  401577:	8b 75 fc             	mov    -0x4(%rbp),%esi
  40157a:	bf 05 00 00 00       	mov    $0x5,%edi
  40157f:	e8 73 ff ff ff       	callq  4014f7 <func4>
  401584:	39 45 f8             	cmp    %eax,-0x8(%rbp)
  401587:	75 02                	jne    40158b <phase_4+0x4a>
  401589:	c9                   	leaveq 
  40158a:	c3                   	retq   
  40158b:	e8 76 03 00 00       	callq  401906 <explode_bomb>
  401590:	eb f7                	jmp    401589 <phase_4+0x48>

0000000000401592 <phase_5>:
  401592:	55                   	push   %rbp
  401593:	48 89 e5             	mov    %rsp,%rbp
  401596:	53                   	push   %rbx
  401597:	48 83 ec 08          	sub    $0x8,%rsp
  40159b:	48 89 fb             	mov    %rdi,%rbx
  40159e:	e8 50 02 00 00       	callq  4017f3 <string_length>
  4015a3:	83 f8 06             	cmp    $0x6,%eax
  4015a6:	75 25                	jne    4015cd <phase_5+0x3b>
  4015a8:	b9 00 00 00 00       	mov    $0x0,%ecx
  4015ad:	b8 00 00 00 00       	mov    $0x0,%eax
  4015b2:	83 f8 05             	cmp    $0x5,%eax
  4015b5:	7f 1d                	jg     4015d4 <phase_5+0x42>
  4015b7:	48 63 d0             	movslq %eax,%rdx
  4015ba:	0f b6 14 13          	movzbl (%rbx,%rdx,1),%edx
  4015be:	83 e2 0f             	and    $0xf,%edx
  4015c1:	03 0c 95 00 32 40 00 	add    0x403200(,%rdx,4),%ecx
  4015c8:	83 c0 01             	add    $0x1,%eax
  4015cb:	eb e5                	jmp    4015b2 <phase_5+0x20>
  4015cd:	e8 34 03 00 00       	callq  401906 <explode_bomb>
  4015d2:	eb d4                	jmp    4015a8 <phase_5+0x16>
  4015d4:	83 f9 2b             	cmp    $0x2b,%ecx
  4015d7:	75 07                	jne    4015e0 <phase_5+0x4e>
  4015d9:	48 83 c4 08          	add    $0x8,%rsp
  4015dd:	5b                   	pop    %rbx
  4015de:	5d                   	pop    %rbp
  4015df:	c3                   	retq   
  4015e0:	e8 21 03 00 00       	callq  401906 <explode_bomb>
  4015e5:	eb f2                	jmp    4015d9 <phase_5+0x47>

00000000004015e7 <phase_6>:
  4015e7:	55                   	push   %rbp
  4015e8:	48 89 e5             	mov    %rsp,%rbp
  4015eb:	41 55                	push   %r13
  4015ed:	41 54                	push   %r12
  4015ef:	53                   	push   %rbx
  4015f0:	48 83 ec 58          	sub    $0x58,%rsp
  4015f4:	48 8d 75 c0          	lea    -0x40(%rbp),%rsi
  4015f8:	e8 2b 03 00 00       	callq  401928 <read_six_numbers>
  4015fd:	41 bc 00 00 00 00    	mov    $0x0,%r12d
  401603:	eb 29                	jmp    40162e <phase_6+0x47>
  401605:	e8 fc 02 00 00       	callq  401906 <explode_bomb>
  40160a:	eb 37                	jmp    401643 <phase_6+0x5c>
  40160c:	e8 f5 02 00 00       	callq  401906 <explode_bomb>
  401611:	83 c3 01             	add    $0x1,%ebx
  401614:	83 fb 05             	cmp    $0x5,%ebx
  401617:	7f 12                	jg     40162b <phase_6+0x44>
  401619:	49 63 c4             	movslq %r12d,%rax
  40161c:	48 63 d3             	movslq %ebx,%rdx
  40161f:	8b 7c 95 c0          	mov    -0x40(%rbp,%rdx,4),%edi
  401623:	39 7c 85 c0          	cmp    %edi,-0x40(%rbp,%rax,4)
  401627:	75 e8                	jne    401611 <phase_6+0x2a>
  401629:	eb e1                	jmp    40160c <phase_6+0x25>
  40162b:	45 89 ec             	mov    %r13d,%r12d
  40162e:	41 83 fc 05          	cmp    $0x5,%r12d
  401632:	7f 19                	jg     40164d <phase_6+0x66>
  401634:	49 63 c4             	movslq %r12d,%rax
  401637:	8b 44 85 c0          	mov    -0x40(%rbp,%rax,4),%eax
  40163b:	83 e8 01             	sub    $0x1,%eax
  40163e:	83 f8 05             	cmp    $0x5,%eax
  401641:	77 c2                	ja     401605 <phase_6+0x1e>
  401643:	45 8d 6c 24 01       	lea    0x1(%r12),%r13d
  401648:	44 89 eb             	mov    %r13d,%ebx
  40164b:	eb c7                	jmp    401614 <phase_6+0x2d>
  40164d:	be 00 00 00 00       	mov    $0x0,%esi
  401652:	eb 18                	jmp    40166c <phase_6+0x85>
  401654:	48 8b 52 08          	mov    0x8(%rdx),%rdx
  401658:	83 c0 01             	add    $0x1,%eax
  40165b:	48 63 ce             	movslq %esi,%rcx
  40165e:	39 44 8d c0          	cmp    %eax,-0x40(%rbp,%rcx,4)
  401662:	7f f0                	jg     401654 <phase_6+0x6d>
  401664:	48 89 54 cd 90       	mov    %rdx,-0x70(%rbp,%rcx,8)
  401669:	83 c6 01             	add    $0x1,%esi
  40166c:	83 fe 05             	cmp    $0x5,%esi
  40166f:	7f 0c                	jg     40167d <phase_6+0x96>
  401671:	b8 01 00 00 00       	mov    $0x1,%eax
  401676:	ba d0 52 40 00       	mov    $0x4052d0,%edx
  40167b:	eb de                	jmp    40165b <phase_6+0x74>
  40167d:	48 8b 5d 90          	mov    -0x70(%rbp),%rbx
  401681:	48 89 d9             	mov    %rbx,%rcx
  401684:	b8 01 00 00 00       	mov    $0x1,%eax
  401689:	eb 12                	jmp    40169d <phase_6+0xb6>
  40168b:	48 63 d0             	movslq %eax,%rdx
  40168e:	48 8b 54 d5 90       	mov    -0x70(%rbp,%rdx,8),%rdx
  401693:	48 89 51 08          	mov    %rdx,0x8(%rcx)
  401697:	83 c0 01             	add    $0x1,%eax
  40169a:	48 89 d1             	mov    %rdx,%rcx
  40169d:	83 f8 05             	cmp    $0x5,%eax
  4016a0:	7e e9                	jle    40168b <phase_6+0xa4>
  4016a2:	48 c7 41 08 00 00 00 	movq   $0x0,0x8(%rcx)
  4016a9:	00 
  4016aa:	41 bc 00 00 00 00    	mov    $0x0,%r12d
  4016b0:	eb 08                	jmp    4016ba <phase_6+0xd3>
  4016b2:	48 8b 5b 08          	mov    0x8(%rbx),%rbx
  4016b6:	41 83 c4 01          	add    $0x1,%r12d
  4016ba:	41 83 fc 04          	cmp    $0x4,%r12d
  4016be:	7f 11                	jg     4016d1 <phase_6+0xea>
  4016c0:	48 8b 43 08          	mov    0x8(%rbx),%rax
  4016c4:	8b 00                	mov    (%rax),%eax
  4016c6:	39 03                	cmp    %eax,(%rbx)
  4016c8:	7d e8                	jge    4016b2 <phase_6+0xcb>
  4016ca:	e8 37 02 00 00       	callq  401906 <explode_bomb>
  4016cf:	eb e1                	jmp    4016b2 <phase_6+0xcb>
  4016d1:	48 83 c4 58          	add    $0x58,%rsp
  4016d5:	5b                   	pop    %rbx
  4016d6:	41 5c                	pop    %r12
  4016d8:	41 5d                	pop    %r13
  4016da:	5d                   	pop    %rbp
  4016db:	c3                   	retq   

00000000004016dc <fun7>:
  4016dc:	48 85 ff             	test   %rdi,%rdi
  4016df:	74 2f                	je     401710 <fun7+0x34>
  4016e1:	55                   	push   %rbp
  4016e2:	48 89 e5             	mov    %rsp,%rbp
  4016e5:	8b 07                	mov    (%rdi),%eax
  4016e7:	39 f0                	cmp    %esi,%eax
  4016e9:	7f 09                	jg     4016f4 <fun7+0x18>
  4016eb:	75 14                	jne    401701 <fun7+0x25>
  4016ed:	b8 00 00 00 00       	mov    $0x0,%eax
  4016f2:	5d                   	pop    %rbp
  4016f3:	c3                   	retq   
  4016f4:	48 8b 7f 08          	mov    0x8(%rdi),%rdi
  4016f8:	e8 df ff ff ff       	callq  4016dc <fun7>
  4016fd:	01 c0                	add    %eax,%eax
  4016ff:	eb f1                	jmp    4016f2 <fun7+0x16>
  401701:	48 8b 7f 10          	mov    0x10(%rdi),%rdi
  401705:	e8 d2 ff ff ff       	callq  4016dc <fun7>
  40170a:	8d 44 00 01          	lea    0x1(%rax,%rax,1),%eax
  40170e:	eb e2                	jmp    4016f2 <fun7+0x16>
  401710:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  401715:	c3                   	retq   

0000000000401716 <secret_phase>:
  401716:	55                   	push   %rbp
  401717:	48 89 e5             	mov    %rsp,%rbp
  40171a:	53                   	push   %rbx
  40171b:	48 83 ec 08          	sub    $0x8,%rsp
  40171f:	e8 40 02 00 00       	callq  401964 <read_line>
  401724:	ba 0a 00 00 00       	mov    $0xa,%edx
  401729:	be 00 00 00 00       	mov    $0x0,%esi
  40172e:	48 89 c7             	mov    %rax,%rdi
  401731:	e8 ba f9 ff ff       	callq  4010f0 <strtol@plt>
  401736:	48 89 c3             	mov    %rax,%rbx
  401739:	8d 40 ff             	lea    -0x1(%rax),%eax
  40173c:	3d e8 03 00 00       	cmp    $0x3e8,%eax
  401741:	77 27                	ja     40176a <secret_phase+0x54>
  401743:	89 de                	mov    %ebx,%esi
  401745:	bf f0 50 40 00       	mov    $0x4050f0,%edi
  40174a:	e8 8d ff ff ff       	callq  4016dc <fun7>
  40174f:	83 f8 03             	cmp    $0x3,%eax
  401752:	75 1d                	jne    401771 <secret_phase+0x5b>
  401754:	bf 88 31 40 00       	mov    $0x403188,%edi
  401759:	e8 02 f9 ff ff       	callq  401060 <puts@plt>
  40175e:	e8 32 03 00 00       	callq  401a95 <phase_defused>
  401763:	48 83 c4 08          	add    $0x8,%rsp
  401767:	5b                   	pop    %rbx
  401768:	5d                   	pop    %rbp
  401769:	c3                   	retq   
  40176a:	e8 97 01 00 00       	callq  401906 <explode_bomb>
  40176f:	eb d2                	jmp    401743 <secret_phase+0x2d>
  401771:	e8 90 01 00 00       	callq  401906 <explode_bomb>
  401776:	eb dc                	jmp    401754 <secret_phase+0x3e>

0000000000401778 <sig_handler>:
  401778:	55                   	push   %rbp
  401779:	48 89 e5             	mov    %rsp,%rbp
  40177c:	bf 40 32 40 00       	mov    $0x403240,%edi
  401781:	e8 da f8 ff ff       	callq  401060 <puts@plt>
  401786:	bf 03 00 00 00       	mov    $0x3,%edi
  40178b:	e8 f0 f9 ff ff       	callq  401180 <sleep@plt>
  401790:	be 02 33 40 00       	mov    $0x403302,%esi
  401795:	bf 01 00 00 00       	mov    $0x1,%edi
  40179a:	b8 00 00 00 00       	mov    $0x0,%eax
  40179f:	e8 8c f9 ff ff       	callq  401130 <__printf_chk@plt>
  4017a4:	48 8b 3d 95 3f 00 00 	mov    0x3f95(%rip),%rdi        # 405740 <stdout@@GLIBC_2.2.5>
  4017ab:	e8 60 f9 ff ff       	callq  401110 <fflush@plt>
  4017b0:	bf 01 00 00 00       	mov    $0x1,%edi
  4017b5:	e8 c6 f9 ff ff       	callq  401180 <sleep@plt>
  4017ba:	bf 0a 33 40 00       	mov    $0x40330a,%edi
  4017bf:	e8 9c f8 ff ff       	callq  401060 <puts@plt>
  4017c4:	bf 10 00 00 00       	mov    $0x10,%edi
  4017c9:	e8 82 f9 ff ff       	callq  401150 <exit@plt>

00000000004017ce <invalid_phase>:
  4017ce:	55                   	push   %rbp
  4017cf:	48 89 e5             	mov    %rsp,%rbp
  4017d2:	48 89 fa             	mov    %rdi,%rdx
  4017d5:	be 12 33 40 00       	mov    $0x403312,%esi
  4017da:	bf 01 00 00 00       	mov    $0x1,%edi
  4017df:	b8 00 00 00 00       	mov    $0x0,%eax
  4017e4:	e8 47 f9 ff ff       	callq  401130 <__printf_chk@plt>
  4017e9:	bf 08 00 00 00       	mov    $0x8,%edi
  4017ee:	e8 5d f9 ff ff       	callq  401150 <exit@plt>

00000000004017f3 <string_length>:
  4017f3:	b8 00 00 00 00       	mov    $0x0,%eax
  4017f8:	80 3f 00             	cmpb   $0x0,(%rdi)
  4017fb:	74 09                	je     401806 <string_length+0x13>
  4017fd:	48 83 c7 01          	add    $0x1,%rdi
  401801:	83 c0 01             	add    $0x1,%eax
  401804:	eb f2                	jmp    4017f8 <string_length+0x5>
  401806:	c3                   	retq   

0000000000401807 <strings_not_equal>:
  401807:	55                   	push   %rbp
  401808:	48 89 e5             	mov    %rsp,%rbp
  40180b:	41 55                	push   %r13
  40180d:	41 54                	push   %r12
  40180f:	53                   	push   %rbx
  401810:	48 83 ec 08          	sub    $0x8,%rsp
  401814:	48 89 fb             	mov    %rdi,%rbx
  401817:	49 89 f4             	mov    %rsi,%r12
  40181a:	e8 d4 ff ff ff       	callq  4017f3 <string_length>
  40181f:	41 89 c5             	mov    %eax,%r13d
  401822:	4c 89 e7             	mov    %r12,%rdi
  401825:	e8 c9 ff ff ff       	callq  4017f3 <string_length>
  40182a:	41 39 c5             	cmp    %eax,%r13d
  40182d:	75 1e                	jne    40184d <strings_not_equal+0x46>
  40182f:	0f b6 03             	movzbl (%rbx),%eax
  401832:	84 c0                	test   %al,%al
  401834:	74 10                	je     401846 <strings_not_equal+0x3f>
  401836:	41 38 04 24          	cmp    %al,(%r12)
  40183a:	75 21                	jne    40185d <strings_not_equal+0x56>
  40183c:	48 83 c3 01          	add    $0x1,%rbx
  401840:	49 83 c4 01          	add    $0x1,%r12
  401844:	eb e9                	jmp    40182f <strings_not_equal+0x28>
  401846:	b8 00 00 00 00       	mov    $0x0,%eax
  40184b:	eb 05                	jmp    401852 <strings_not_equal+0x4b>
  40184d:	b8 01 00 00 00       	mov    $0x1,%eax
  401852:	48 83 c4 08          	add    $0x8,%rsp
  401856:	5b                   	pop    %rbx
  401857:	41 5c                	pop    %r12
  401859:	41 5d                	pop    %r13
  40185b:	5d                   	pop    %rbp
  40185c:	c3                   	retq   
  40185d:	b8 01 00 00 00       	mov    $0x1,%eax
  401862:	eb ee                	jmp    401852 <strings_not_equal+0x4b>

0000000000401864 <initialize_bomb>:
  401864:	55                   	push   %rbp
  401865:	48 89 e5             	mov    %rsp,%rbp
  401868:	be 78 17 40 00       	mov    $0x401778,%esi
  40186d:	bf 02 00 00 00       	mov    $0x2,%edi
  401872:	e8 49 f8 ff ff       	callq  4010c0 <signal@plt>
  401877:	5d                   	pop    %rbp
  401878:	c3                   	retq   

0000000000401879 <initialize_bomb_solve>:
  401879:	c3                   	retq   

000000000040187a <blank_line>:
  40187a:	55                   	push   %rbp
  40187b:	48 89 e5             	mov    %rsp,%rbp
  40187e:	41 54                	push   %r12
  401880:	53                   	push   %rbx
  401881:	49 89 fc             	mov    %rdi,%r12
  401884:	41 0f b6 1c 24       	movzbl (%r12),%ebx
  401889:	84 db                	test   %bl,%bl
  40188b:	74 1e                	je     4018ab <blank_line+0x31>
  40188d:	e8 fe f8 ff ff       	callq  401190 <__ctype_b_loc@plt>
  401892:	48 8b 00             	mov    (%rax),%rax
  401895:	49 83 c4 01          	add    $0x1,%r12
  401899:	48 0f be db          	movsbq %bl,%rbx
  40189d:	f6 44 58 01 20       	testb  $0x20,0x1(%rax,%rbx,2)
  4018a2:	75 e0                	jne    401884 <blank_line+0xa>
  4018a4:	b8 00 00 00 00       	mov    $0x0,%eax
  4018a9:	eb 05                	jmp    4018b0 <blank_line+0x36>
  4018ab:	b8 01 00 00 00       	mov    $0x1,%eax
  4018b0:	5b                   	pop    %rbx
  4018b1:	41 5c                	pop    %r12
  4018b3:	5d                   	pop    %rbp
  4018b4:	c3                   	retq   

00000000004018b5 <skip>:
  4018b5:	55                   	push   %rbp
  4018b6:	48 89 e5             	mov    %rsp,%rbp
  4018b9:	53                   	push   %rbx
  4018ba:	48 83 ec 08          	sub    $0x8,%rsp
  4018be:	48 63 05 a7 3e 00 00 	movslq 0x3ea7(%rip),%rax        # 40576c <num_input_strings>
  4018c5:	48 8d 04 80          	lea    (%rax,%rax,4),%rax
  4018c9:	48 c1 e0 04          	shl    $0x4,%rax
  4018cd:	48 89 c7             	mov    %rax,%rdi
  4018d0:	48 81 c7 80 57 40 00 	add    $0x405780,%rdi
  4018d7:	48 8b 15 92 3e 00 00 	mov    0x3e92(%rip),%rdx        # 405770 <infile>
  4018de:	be 50 00 00 00       	mov    $0x50,%esi
  4018e3:	e8 c8 f7 ff ff       	callq  4010b0 <fgets@plt>
  4018e8:	48 89 c3             	mov    %rax,%rbx
  4018eb:	48 85 c0             	test   %rax,%rax
  4018ee:	74 0c                	je     4018fc <skip+0x47>
  4018f0:	48 89 c7             	mov    %rax,%rdi
  4018f3:	e8 82 ff ff ff       	callq  40187a <blank_line>
  4018f8:	85 c0                	test   %eax,%eax
  4018fa:	75 c2                	jne    4018be <skip+0x9>
  4018fc:	48 89 d8             	mov    %rbx,%rax
  4018ff:	48 83 c4 08          	add    $0x8,%rsp
  401903:	5b                   	pop    %rbx
  401904:	5d                   	pop    %rbp
  401905:	c3                   	retq   

0000000000401906 <explode_bomb>:
  401906:	55                   	push   %rbp
  401907:	48 89 e5             	mov    %rsp,%rbp
  40190a:	bf 23 33 40 00       	mov    $0x403323,%edi
  40190f:	e8 4c f7 ff ff       	callq  401060 <puts@plt>
  401914:	bf 2c 33 40 00       	mov    $0x40332c,%edi
  401919:	e8 42 f7 ff ff       	callq  401060 <puts@plt>
  40191e:	bf 08 00 00 00       	mov    $0x8,%edi
  401923:	e8 28 f8 ff ff       	callq  401150 <exit@plt>

0000000000401928 <read_six_numbers>:
  401928:	55                   	push   %rbp
  401929:	48 89 e5             	mov    %rsp,%rbp
  40192c:	48 89 f2             	mov    %rsi,%rdx
  40192f:	48 8d 4e 04          	lea    0x4(%rsi),%rcx
  401933:	48 8d 46 14          	lea    0x14(%rsi),%rax
  401937:	50                   	push   %rax
  401938:	48 8d 46 10          	lea    0x10(%rsi),%rax
  40193c:	50                   	push   %rax
  40193d:	4c 8d 4e 0c          	lea    0xc(%rsi),%r9
  401941:	4c 8d 46 08          	lea    0x8(%rsi),%r8
  401945:	be 43 33 40 00       	mov    $0x403343,%esi
  40194a:	b8 00 00 00 00       	mov    $0x0,%eax
  40194f:	e8 cc f7 ff ff       	callq  401120 <__isoc99_sscanf@plt>
  401954:	48 83 c4 10          	add    $0x10,%rsp
  401958:	83 f8 05             	cmp    $0x5,%eax
  40195b:	7e 02                	jle    40195f <read_six_numbers+0x37>
  40195d:	c9                   	leaveq 
  40195e:	c3                   	retq   
  40195f:	e8 a2 ff ff ff       	callq  401906 <explode_bomb>

0000000000401964 <read_line>:
  401964:	55                   	push   %rbp
  401965:	48 89 e5             	mov    %rsp,%rbp
  401968:	b8 00 00 00 00       	mov    $0x0,%eax
  40196d:	e8 43 ff ff ff       	callq  4018b5 <skip>
  401972:	48 85 c0             	test   %rax,%rax
  401975:	74 66                	je     4019dd <read_line+0x79>
  401977:	8b 35 ef 3d 00 00    	mov    0x3def(%rip),%esi        # 40576c <num_input_strings>
  40197d:	48 63 c6             	movslq %esi,%rax
  401980:	48 8d 04 80          	lea    (%rax,%rax,4),%rax
  401984:	48 c1 e0 04          	shl    $0x4,%rax
  401988:	48 89 c2             	mov    %rax,%rdx
  40198b:	48 81 c2 80 57 40 00 	add    $0x405780,%rdx
  401992:	48 c7 c1 ff ff ff ff 	mov    $0xffffffffffffffff,%rcx
  401999:	b8 00 00 00 00       	mov    $0x0,%eax
  40199e:	48 89 d7             	mov    %rdx,%rdi
  4019a1:	f2 ae                	repnz scas %es:(%rdi),%al
  4019a3:	48 f7 d1             	not    %rcx
  4019a6:	48 83 e9 01          	sub    $0x1,%rcx
  4019aa:	83 f9 4e             	cmp    $0x4e,%ecx
  4019ad:	0f 8f 9c 00 00 00    	jg     401a4f <read_line+0xeb>
  4019b3:	83 e9 01             	sub    $0x1,%ecx
  4019b6:	48 63 c9             	movslq %ecx,%rcx
  4019b9:	48 63 c6             	movslq %esi,%rax
  4019bc:	48 8d 3c 80          	lea    (%rax,%rax,4),%rdi
  4019c0:	48 89 f8             	mov    %rdi,%rax
  4019c3:	48 c1 e0 04          	shl    $0x4,%rax
  4019c7:	c6 84 01 80 57 40 00 	movb   $0x0,0x405780(%rcx,%rax,1)
  4019ce:	00 
  4019cf:	83 c6 01             	add    $0x1,%esi
  4019d2:	89 35 94 3d 00 00    	mov    %esi,0x3d94(%rip)        # 40576c <num_input_strings>
  4019d8:	48 89 d0             	mov    %rdx,%rax
  4019db:	5d                   	pop    %rbp
  4019dc:	c3                   	retq   
  4019dd:	48 8b 05 6c 3d 00 00 	mov    0x3d6c(%rip),%rax        # 405750 <stdin@@GLIBC_2.2.5>
  4019e4:	48 39 05 85 3d 00 00 	cmp    %rax,0x3d85(%rip)        # 405770 <infile>
  4019eb:	74 19                	je     401a06 <read_line+0xa2>
  4019ed:	bf 73 33 40 00       	mov    $0x403373,%edi
  4019f2:	e8 39 f6 ff ff       	callq  401030 <getenv@plt>
  4019f7:	48 85 c0             	test   %rax,%rax
  4019fa:	74 1e                	je     401a1a <read_line+0xb6>
  4019fc:	bf 00 00 00 00       	mov    $0x0,%edi
  401a01:	e8 4a f7 ff ff       	callq  401150 <exit@plt>
  401a06:	bf 55 33 40 00       	mov    $0x403355,%edi
  401a0b:	e8 50 f6 ff ff       	callq  401060 <puts@plt>
  401a10:	bf 08 00 00 00       	mov    $0x8,%edi
  401a15:	e8 36 f7 ff ff       	callq  401150 <exit@plt>
  401a1a:	48 8b 05 2f 3d 00 00 	mov    0x3d2f(%rip),%rax        # 405750 <stdin@@GLIBC_2.2.5>
  401a21:	48 89 05 48 3d 00 00 	mov    %rax,0x3d48(%rip)        # 405770 <infile>
  401a28:	b8 00 00 00 00       	mov    $0x0,%eax
  401a2d:	e8 83 fe ff ff       	callq  4018b5 <skip>
  401a32:	48 85 c0             	test   %rax,%rax
  401a35:	0f 85 3c ff ff ff    	jne    401977 <read_line+0x13>
  401a3b:	bf 55 33 40 00       	mov    $0x403355,%edi
  401a40:	e8 1b f6 ff ff       	callq  401060 <puts@plt>
  401a45:	bf 00 00 00 00       	mov    $0x0,%edi
  401a4a:	e8 01 f7 ff ff       	callq  401150 <exit@plt>
  401a4f:	bf 7e 33 40 00       	mov    $0x40337e,%edi
  401a54:	e8 07 f6 ff ff       	callq  401060 <puts@plt>
  401a59:	8b 05 0d 3d 00 00    	mov    0x3d0d(%rip),%eax        # 40576c <num_input_strings>
  401a5f:	8d 50 01             	lea    0x1(%rax),%edx
  401a62:	89 15 04 3d 00 00    	mov    %edx,0x3d04(%rip)        # 40576c <num_input_strings>
  401a68:	48 98                	cltq   
  401a6a:	48 6b c0 50          	imul   $0x50,%rax,%rax
  401a6e:	48 be 2a 2a 2a 74 72 	movabs $0x636e7572742a2a2a,%rsi
  401a75:	75 6e 63 
  401a78:	48 bf 61 74 65 64 2a 	movabs $0x2a2a2a64657461,%rdi
  401a7f:	2a 2a 00 
  401a82:	48 89 b0 80 57 40 00 	mov    %rsi,0x405780(%rax)
  401a89:	48 89 b8 88 57 40 00 	mov    %rdi,0x405788(%rax)
  401a90:	e8 71 fe ff ff       	callq  401906 <explode_bomb>

0000000000401a95 <phase_defused>:
  401a95:	83 3d d0 3c 00 00 06 	cmpl   $0x6,0x3cd0(%rip)        # 40576c <num_input_strings>
  401a9c:	74 01                	je     401a9f <phase_defused+0xa>
  401a9e:	c3                   	retq   
  401a9f:	55                   	push   %rbp
  401aa0:	48 89 e5             	mov    %rsp,%rbp
  401aa3:	48 83 ec 60          	sub    $0x60,%rsp
  401aa7:	4c 8d 45 b0          	lea    -0x50(%rbp),%r8
  401aab:	48 8d 4d a8          	lea    -0x58(%rbp),%rcx
  401aaf:	48 8d 55 ac          	lea    -0x54(%rbp),%rdx
  401ab3:	be 99 33 40 00       	mov    $0x403399,%esi
  401ab8:	bf 70 58 40 00       	mov    $0x405870,%edi
  401abd:	b8 00 00 00 00       	mov    $0x0,%eax
  401ac2:	e8 59 f6 ff ff       	callq  401120 <__isoc99_sscanf@plt>
  401ac7:	83 f8 03             	cmp    $0x3,%eax
  401aca:	74 0c                	je     401ad8 <phase_defused+0x43>
  401acc:	bf d8 32 40 00       	mov    $0x4032d8,%edi
  401ad1:	e8 8a f5 ff ff       	callq  401060 <puts@plt>
  401ad6:	c9                   	leaveq 
  401ad7:	c3                   	retq   
  401ad8:	be a2 33 40 00       	mov    $0x4033a2,%esi
  401add:	48 8d 7d b0          	lea    -0x50(%rbp),%rdi
  401ae1:	e8 21 fd ff ff       	callq  401807 <strings_not_equal>
  401ae6:	85 c0                	test   %eax,%eax
  401ae8:	75 e2                	jne    401acc <phase_defused+0x37>
  401aea:	bf 78 32 40 00       	mov    $0x403278,%edi
  401aef:	e8 6c f5 ff ff       	callq  401060 <puts@plt>
  401af4:	bf a0 32 40 00       	mov    $0x4032a0,%edi
  401af9:	e8 62 f5 ff ff       	callq  401060 <puts@plt>
  401afe:	b8 00 00 00 00       	mov    $0x0,%eax
  401b03:	e8 0e fc ff ff       	callq  401716 <secret_phase>
  401b08:	eb c2                	jmp    401acc <phase_defused+0x37>

0000000000401b0a <rio_readinitb>:
  401b0a:	89 37                	mov    %esi,(%rdi)
  401b0c:	c7 47 04 00 00 00 00 	movl   $0x0,0x4(%rdi)
  401b13:	48 8d 47 10          	lea    0x10(%rdi),%rax
  401b17:	48 89 47 08          	mov    %rax,0x8(%rdi)
  401b1b:	c3                   	retq   

0000000000401b1c <sigalrm_handler>:
  401b1c:	55                   	push   %rbp
  401b1d:	48 89 e5             	mov    %rsp,%rbp
  401b20:	b9 00 00 00 00       	mov    $0x0,%ecx
  401b25:	ba f8 33 40 00       	mov    $0x4033f8,%edx
  401b2a:	be 01 00 00 00       	mov    $0x1,%esi
  401b2f:	48 8b 3d 2a 3c 00 00 	mov    0x3c2a(%rip),%rdi        # 405760 <stderr@@GLIBC_2.2.5>
  401b36:	b8 00 00 00 00       	mov    $0x0,%eax
  401b3b:	e8 30 f6 ff ff       	callq  401170 <__fprintf_chk@plt>
  401b40:	bf 01 00 00 00       	mov    $0x1,%edi
  401b45:	e8 06 f6 ff ff       	callq  401150 <exit@plt>

0000000000401b4a <rio_writen>:
  401b4a:	55                   	push   %rbp
  401b4b:	48 89 e5             	mov    %rsp,%rbp
  401b4e:	41 56                	push   %r14
  401b50:	41 55                	push   %r13
  401b52:	41 54                	push   %r12
  401b54:	53                   	push   %rbx
  401b55:	41 89 fd             	mov    %edi,%r13d
  401b58:	49 89 f4             	mov    %rsi,%r12
  401b5b:	49 89 d6             	mov    %rdx,%r14
  401b5e:	48 89 d3             	mov    %rdx,%rbx
  401b61:	eb 06                	jmp    401b69 <rio_writen+0x1f>
  401b63:	48 29 c3             	sub    %rax,%rbx
  401b66:	49 01 c4             	add    %rax,%r12
  401b69:	48 85 db             	test   %rbx,%rbx
  401b6c:	74 24                	je     401b92 <rio_writen+0x48>
  401b6e:	48 89 da             	mov    %rbx,%rdx
  401b71:	4c 89 e6             	mov    %r12,%rsi
  401b74:	44 89 ef             	mov    %r13d,%edi
  401b77:	e8 f4 f4 ff ff       	callq  401070 <write@plt>
  401b7c:	48 85 c0             	test   %rax,%rax
  401b7f:	7f e2                	jg     401b63 <rio_writen+0x19>
  401b81:	e8 ba f4 ff ff       	callq  401040 <__errno_location@plt>
  401b86:	83 38 04             	cmpl   $0x4,(%rax)
  401b89:	75 13                	jne    401b9e <rio_writen+0x54>
  401b8b:	b8 00 00 00 00       	mov    $0x0,%eax
  401b90:	eb d1                	jmp    401b63 <rio_writen+0x19>
  401b92:	4c 89 f0             	mov    %r14,%rax
  401b95:	5b                   	pop    %rbx
  401b96:	41 5c                	pop    %r12
  401b98:	41 5d                	pop    %r13
  401b9a:	41 5e                	pop    %r14
  401b9c:	5d                   	pop    %rbp
  401b9d:	c3                   	retq   
  401b9e:	48 c7 c0 ff ff ff ff 	mov    $0xffffffffffffffff,%rax
  401ba5:	eb ee                	jmp    401b95 <rio_writen+0x4b>

0000000000401ba7 <urlencode>:
  401ba7:	55                   	push   %rbp
  401ba8:	48 89 e5             	mov    %rsp,%rbp
  401bab:	41 55                	push   %r13
  401bad:	41 54                	push   %r12
  401baf:	53                   	push   %rbx
  401bb0:	48 83 ec 18          	sub    $0x18,%rsp
  401bb4:	48 89 fb             	mov    %rdi,%rbx
  401bb7:	49 89 f4             	mov    %rsi,%r12
  401bba:	48 c7 c1 ff ff ff ff 	mov    $0xffffffffffffffff,%rcx
  401bc1:	b8 00 00 00 00       	mov    $0x0,%eax
  401bc6:	f2 ae                	repnz scas %es:(%rdi),%al
  401bc8:	48 89 ce             	mov    %rcx,%rsi
  401bcb:	48 f7 d6             	not    %rsi
  401bce:	8d 46 ff             	lea    -0x1(%rsi),%eax
  401bd1:	eb 10                	jmp    401be3 <urlencode+0x3c>
  401bd3:	45 88 04 24          	mov    %r8b,(%r12)
  401bd7:	4d 8d 64 24 01       	lea    0x1(%r12),%r12
  401bdc:	48 83 c3 01          	add    $0x1,%rbx
  401be0:	44 89 e8             	mov    %r13d,%eax
  401be3:	44 8d 68 ff          	lea    -0x1(%rax),%r13d
  401be7:	85 c0                	test   %eax,%eax
  401be9:	0f 84 ad 00 00 00    	je     401c9c <urlencode+0xf5>
  401bef:	44 0f b6 03          	movzbl (%rbx),%r8d
  401bf3:	41 80 f8 2a          	cmp    $0x2a,%r8b
  401bf7:	0f 94 c2             	sete   %dl
  401bfa:	41 80 f8 2d          	cmp    $0x2d,%r8b
  401bfe:	0f 94 c0             	sete   %al
  401c01:	08 c2                	or     %al,%dl
  401c03:	75 ce                	jne    401bd3 <urlencode+0x2c>
  401c05:	41 80 f8 2e          	cmp    $0x2e,%r8b
  401c09:	74 c8                	je     401bd3 <urlencode+0x2c>
  401c0b:	41 80 f8 5f          	cmp    $0x5f,%r8b
  401c0f:	74 c2                	je     401bd3 <urlencode+0x2c>
  401c11:	41 8d 40 d0          	lea    -0x30(%r8),%eax
  401c15:	3c 09                	cmp    $0x9,%al
  401c17:	76 ba                	jbe    401bd3 <urlencode+0x2c>
  401c19:	41 8d 40 bf          	lea    -0x41(%r8),%eax
  401c1d:	3c 19                	cmp    $0x19,%al
  401c1f:	76 b2                	jbe    401bd3 <urlencode+0x2c>
  401c21:	41 8d 40 9f          	lea    -0x61(%r8),%eax
  401c25:	3c 19                	cmp    $0x19,%al
  401c27:	76 aa                	jbe    401bd3 <urlencode+0x2c>
  401c29:	41 80 f8 20          	cmp    $0x20,%r8b
  401c2d:	74 59                	je     401c88 <urlencode+0xe1>
  401c2f:	41 8d 40 e0          	lea    -0x20(%r8),%eax
  401c33:	3c 5f                	cmp    $0x5f,%al
  401c35:	0f 96 c2             	setbe  %dl
  401c38:	41 80 f8 09          	cmp    $0x9,%r8b
  401c3c:	0f 94 c0             	sete   %al
  401c3f:	08 c2                	or     %al,%dl
  401c41:	74 54                	je     401c97 <urlencode+0xf0>
  401c43:	45 0f b6 c0          	movzbl %r8b,%r8d
  401c47:	b9 c8 34 40 00       	mov    $0x4034c8,%ecx
  401c4c:	ba 08 00 00 00       	mov    $0x8,%edx
  401c51:	be 01 00 00 00       	mov    $0x1,%esi
  401c56:	48 8d 7d d8          	lea    -0x28(%rbp),%rdi
  401c5a:	b8 00 00 00 00       	mov    $0x0,%eax
  401c5f:	e8 3c f5 ff ff       	callq  4011a0 <__sprintf_chk@plt>
  401c64:	0f b6 45 d8          	movzbl -0x28(%rbp),%eax
  401c68:	41 88 04 24          	mov    %al,(%r12)
  401c6c:	0f b6 45 d9          	movzbl -0x27(%rbp),%eax
  401c70:	41 88 44 24 01       	mov    %al,0x1(%r12)
  401c75:	0f b6 45 da          	movzbl -0x26(%rbp),%eax
  401c79:	41 88 44 24 02       	mov    %al,0x2(%r12)
  401c7e:	4d 8d 64 24 03       	lea    0x3(%r12),%r12
  401c83:	e9 54 ff ff ff       	jmpq   401bdc <urlencode+0x35>
  401c88:	41 c6 04 24 2b       	movb   $0x2b,(%r12)
  401c8d:	4d 8d 64 24 01       	lea    0x1(%r12),%r12
  401c92:	e9 45 ff ff ff       	jmpq   401bdc <urlencode+0x35>
  401c97:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  401c9c:	48 83 c4 18          	add    $0x18,%rsp
  401ca0:	5b                   	pop    %rbx
  401ca1:	41 5c                	pop    %r12
  401ca3:	41 5d                	pop    %r13
  401ca5:	5d                   	pop    %rbp
  401ca6:	c3                   	retq   

0000000000401ca7 <rio_read>:
  401ca7:	55                   	push   %rbp
  401ca8:	48 89 e5             	mov    %rsp,%rbp
  401cab:	41 56                	push   %r14
  401cad:	41 55                	push   %r13
  401caf:	41 54                	push   %r12
  401cb1:	53                   	push   %rbx
  401cb2:	48 89 fb             	mov    %rdi,%rbx
  401cb5:	49 89 f6             	mov    %rsi,%r14
  401cb8:	49 89 d5             	mov    %rdx,%r13
  401cbb:	eb 0a                	jmp    401cc7 <rio_read+0x20>
  401cbd:	e8 7e f3 ff ff       	callq  401040 <__errno_location@plt>
  401cc2:	83 38 04             	cmpl   $0x4,(%rax)
  401cc5:	75 5c                	jne    401d23 <rio_read+0x7c>
  401cc7:	44 8b 63 04          	mov    0x4(%rbx),%r12d
  401ccb:	45 85 e4             	test   %r12d,%r12d
  401cce:	7f 22                	jg     401cf2 <rio_read+0x4b>
  401cd0:	4c 8d 63 10          	lea    0x10(%rbx),%r12
  401cd4:	8b 3b                	mov    (%rbx),%edi
  401cd6:	ba 00 20 00 00       	mov    $0x2000,%edx
  401cdb:	4c 89 e6             	mov    %r12,%rsi
  401cde:	e8 bd f3 ff ff       	callq  4010a0 <read@plt>
  401ce3:	89 43 04             	mov    %eax,0x4(%rbx)
  401ce6:	85 c0                	test   %eax,%eax
  401ce8:	78 d3                	js     401cbd <rio_read+0x16>
  401cea:	74 40                	je     401d2c <rio_read+0x85>
  401cec:	4c 89 63 08          	mov    %r12,0x8(%rbx)
  401cf0:	eb d5                	jmp    401cc7 <rio_read+0x20>
  401cf2:	49 63 c4             	movslq %r12d,%rax
  401cf5:	4c 39 e8             	cmp    %r13,%rax
  401cf8:	72 03                	jb     401cfd <rio_read+0x56>
  401cfa:	45 89 ec             	mov    %r13d,%r12d
  401cfd:	4d 63 ec             	movslq %r12d,%r13
  401d00:	48 8b 73 08          	mov    0x8(%rbx),%rsi
  401d04:	4c 89 ea             	mov    %r13,%rdx
  401d07:	4c 89 f7             	mov    %r14,%rdi
  401d0a:	e8 f1 f3 ff ff       	callq  401100 <memcpy@plt>
  401d0f:	4c 01 6b 08          	add    %r13,0x8(%rbx)
  401d13:	44 29 63 04          	sub    %r12d,0x4(%rbx)
  401d17:	4c 89 e8             	mov    %r13,%rax
  401d1a:	5b                   	pop    %rbx
  401d1b:	41 5c                	pop    %r12
  401d1d:	41 5d                	pop    %r13
  401d1f:	41 5e                	pop    %r14
  401d21:	5d                   	pop    %rbp
  401d22:	c3                   	retq   
  401d23:	48 c7 c0 ff ff ff ff 	mov    $0xffffffffffffffff,%rax
  401d2a:	eb ee                	jmp    401d1a <rio_read+0x73>
  401d2c:	b8 00 00 00 00       	mov    $0x0,%eax
  401d31:	eb e7                	jmp    401d1a <rio_read+0x73>

0000000000401d33 <rio_readlineb>:
  401d33:	55                   	push   %rbp
  401d34:	48 89 e5             	mov    %rsp,%rbp
  401d37:	41 56                	push   %r14
  401d39:	41 55                	push   %r13
  401d3b:	41 54                	push   %r12
  401d3d:	53                   	push   %rbx
  401d3e:	48 83 ec 10          	sub    $0x10,%rsp
  401d42:	49 89 fe             	mov    %rdi,%r14
  401d45:	49 89 f4             	mov    %rsi,%r12
  401d48:	49 89 d5             	mov    %rdx,%r13
  401d4b:	bb 01 00 00 00       	mov    $0x1,%ebx
  401d50:	48 63 c3             	movslq %ebx,%rax
  401d53:	4c 39 e8             	cmp    %r13,%rax
  401d56:	73 42                	jae    401d9a <rio_readlineb+0x67>
  401d58:	ba 01 00 00 00       	mov    $0x1,%edx
  401d5d:	48 8d 75 df          	lea    -0x21(%rbp),%rsi
  401d61:	4c 89 f7             	mov    %r14,%rdi
  401d64:	e8 3e ff ff ff       	callq  401ca7 <rio_read>
  401d69:	83 f8 01             	cmp    $0x1,%eax
  401d6c:	75 19                	jne    401d87 <rio_readlineb+0x54>
  401d6e:	49 8d 54 24 01       	lea    0x1(%r12),%rdx
  401d73:	0f b6 45 df          	movzbl -0x21(%rbp),%eax
  401d77:	41 88 04 24          	mov    %al,(%r12)
  401d7b:	3c 0a                	cmp    $0xa,%al
  401d7d:	74 18                	je     401d97 <rio_readlineb+0x64>
  401d7f:	83 c3 01             	add    $0x1,%ebx
  401d82:	49 89 d4             	mov    %rdx,%r12
  401d85:	eb c9                	jmp    401d50 <rio_readlineb+0x1d>
  401d87:	85 c0                	test   %eax,%eax
  401d89:	75 24                	jne    401daf <rio_readlineb+0x7c>
  401d8b:	83 fb 01             	cmp    $0x1,%ebx
  401d8e:	75 0a                	jne    401d9a <rio_readlineb+0x67>
  401d90:	b8 00 00 00 00       	mov    $0x0,%eax
  401d95:	eb 0b                	jmp    401da2 <rio_readlineb+0x6f>
  401d97:	49 89 d4             	mov    %rdx,%r12
  401d9a:	41 c6 04 24 00       	movb   $0x0,(%r12)
  401d9f:	48 63 c3             	movslq %ebx,%rax
  401da2:	48 83 c4 10          	add    $0x10,%rsp
  401da6:	5b                   	pop    %rbx
  401da7:	41 5c                	pop    %r12
  401da9:	41 5d                	pop    %r13
  401dab:	41 5e                	pop    %r14
  401dad:	5d                   	pop    %rbp
  401dae:	c3                   	retq   
  401daf:	48 c7 c0 ff ff ff ff 	mov    $0xffffffffffffffff,%rax
  401db6:	eb ea                	jmp    401da2 <rio_readlineb+0x6f>

0000000000401db8 <submitr>:
  401db8:	55                   	push   %rbp
  401db9:	48 89 e5             	mov    %rsp,%rbp
  401dbc:	41 57                	push   %r15
  401dbe:	41 56                	push   %r14
  401dc0:	41 55                	push   %r13
  401dc2:	41 54                	push   %r12
  401dc4:	53                   	push   %rbx
  401dc5:	48 81 ec 58 a0 00 00 	sub    $0xa058,%rsp
  401dcc:	48 89 bd 90 5f ff ff 	mov    %rdi,-0xa070(%rbp)
  401dd3:	89 b5 8c 5f ff ff    	mov    %esi,-0xa074(%rbp)
  401dd9:	48 89 95 98 5f ff ff 	mov    %rdx,-0xa068(%rbp)
  401de0:	49 89 cf             	mov    %rcx,%r15
  401de3:	4d 89 c6             	mov    %r8,%r14
  401de6:	4d 89 cd             	mov    %r9,%r13
  401de9:	4c 8b 65 10          	mov    0x10(%rbp),%r12
  401ded:	c7 85 ac 7f ff ff 00 	movl   $0x0,-0x8054(%rbp)
  401df4:	00 00 00 
  401df7:	ba 00 00 00 00       	mov    $0x0,%edx
  401dfc:	be 01 00 00 00       	mov    $0x1,%esi
  401e01:	bf 02 00 00 00       	mov    $0x2,%edi
  401e06:	e8 a5 f3 ff ff       	callq  4011b0 <socket@plt>
  401e0b:	85 c0                	test   %eax,%eax
  401e0d:	0f 88 89 02 00 00    	js     40209c <submitr+0x2e4>
  401e13:	89 c3                	mov    %eax,%ebx
  401e15:	48 8b bd 90 5f ff ff 	mov    -0xa070(%rbp),%rdi
  401e1c:	e8 af f2 ff ff       	callq  4010d0 <gethostbyname@plt>
  401e21:	48 85 c0             	test   %rax,%rax
  401e24:	0f 84 c8 02 00 00    	je     4020f2 <submitr+0x33a>
  401e2a:	48 c7 45 c2 00 00 00 	movq   $0x0,-0x3e(%rbp)
  401e31:	00 
  401e32:	c7 45 ca 00 00 00 00 	movl   $0x0,-0x36(%rbp)
  401e39:	66 c7 45 ce 00 00    	movw   $0x0,-0x32(%rbp)
  401e3f:	66 c7 45 c0 02 00    	movw   $0x2,-0x40(%rbp)
  401e45:	48 63 50 14          	movslq 0x14(%rax),%rdx
  401e49:	48 8b 40 18          	mov    0x18(%rax),%rax
  401e4d:	48 8b 30             	mov    (%rax),%rsi
  401e50:	48 8d 45 c0          	lea    -0x40(%rbp),%rax
  401e54:	48 8d 78 04          	lea    0x4(%rax),%rdi
  401e58:	b9 0c 00 00 00       	mov    $0xc,%ecx
  401e5d:	e8 7e f2 ff ff       	callq  4010e0 <__memmove_chk@plt>
  401e62:	0f b7 85 8c 5f ff ff 	movzwl -0xa074(%rbp),%eax
  401e69:	66 c1 c0 08          	rol    $0x8,%ax
  401e6d:	66 89 45 c2          	mov    %ax,-0x3e(%rbp)
  401e71:	ba 10 00 00 00       	mov    $0x10,%edx
  401e76:	48 8d 75 c0          	lea    -0x40(%rbp),%rsi
  401e7a:	89 df                	mov    %ebx,%edi
  401e7c:	e8 df f2 ff ff       	callq  401160 <connect@plt>
  401e81:	85 c0                	test   %eax,%eax
  401e83:	0f 88 db 02 00 00    	js     402164 <submitr+0x3ac>
  401e89:	48 c7 c6 ff ff ff ff 	mov    $0xffffffffffffffff,%rsi
  401e90:	b8 00 00 00 00       	mov    $0x0,%eax
  401e95:	48 89 f1             	mov    %rsi,%rcx
  401e98:	4c 89 ef             	mov    %r13,%rdi
  401e9b:	f2 ae                	repnz scas %es:(%rdi),%al
  401e9d:	48 89 ca             	mov    %rcx,%rdx
  401ea0:	48 f7 d2             	not    %rdx
  401ea3:	48 89 f1             	mov    %rsi,%rcx
  401ea6:	48 8b bd 98 5f ff ff 	mov    -0xa068(%rbp),%rdi
  401ead:	f2 ae                	repnz scas %es:(%rdi),%al
  401eaf:	48 f7 d1             	not    %rcx
  401eb2:	49 89 c8             	mov    %rcx,%r8
  401eb5:	48 89 f1             	mov    %rsi,%rcx
  401eb8:	4c 89 ff             	mov    %r15,%rdi
  401ebb:	f2 ae                	repnz scas %es:(%rdi),%al
  401ebd:	48 f7 d1             	not    %rcx
  401ec0:	4d 8d 44 08 fe       	lea    -0x2(%r8,%rcx,1),%r8
  401ec5:	48 89 f1             	mov    %rsi,%rcx
  401ec8:	4c 89 f7             	mov    %r14,%rdi
  401ecb:	f2 ae                	repnz scas %es:(%rdi),%al
  401ecd:	48 89 c8             	mov    %rcx,%rax
  401ed0:	48 f7 d0             	not    %rax
  401ed3:	49 8d 4c 00 ff       	lea    -0x1(%r8,%rax,1),%rcx
  401ed8:	48 8d 44 52 fd       	lea    -0x3(%rdx,%rdx,2),%rax
  401edd:	48 8d 84 01 80 00 00 	lea    0x80(%rcx,%rax,1),%rax
  401ee4:	00 
  401ee5:	48 3d 00 20 00 00    	cmp    $0x2000,%rax
  401eeb:	0f 87 d6 02 00 00    	ja     4021c7 <submitr+0x40f>
  401ef1:	48 8d b5 b0 9f ff ff 	lea    -0x6050(%rbp),%rsi
  401ef8:	b9 00 04 00 00       	mov    $0x400,%ecx
  401efd:	b8 00 00 00 00       	mov    $0x0,%eax
  401f02:	48 89 f7             	mov    %rsi,%rdi
  401f05:	f3 48 ab             	rep stos %rax,%es:(%rdi)
  401f08:	4c 89 ef             	mov    %r13,%rdi
  401f0b:	e8 97 fc ff ff       	callq  401ba7 <urlencode>
  401f10:	85 c0                	test   %eax,%eax
  401f12:	0f 88 28 03 00 00    	js     402240 <submitr+0x488>
  401f18:	4c 8d ad b0 bf ff ff 	lea    -0x4050(%rbp),%r13
  401f1f:	48 8d 85 b0 9f ff ff 	lea    -0x6050(%rbp),%rax
  401f26:	50                   	push   %rax
  401f27:	41 56                	push   %r14
  401f29:	4d 89 f9             	mov    %r15,%r9
  401f2c:	4c 8b 85 98 5f ff ff 	mov    -0xa068(%rbp),%r8
  401f33:	b9 20 34 40 00       	mov    $0x403420,%ecx
  401f38:	ba 00 20 00 00       	mov    $0x2000,%edx
  401f3d:	be 01 00 00 00       	mov    $0x1,%esi
  401f42:	4c 89 ef             	mov    %r13,%rdi
  401f45:	b8 00 00 00 00       	mov    $0x0,%eax
  401f4a:	e8 51 f2 ff ff       	callq  4011a0 <__sprintf_chk@plt>
  401f4f:	48 c7 c1 ff ff ff ff 	mov    $0xffffffffffffffff,%rcx
  401f56:	b8 00 00 00 00       	mov    $0x0,%eax
  401f5b:	4c 89 ef             	mov    %r13,%rdi
  401f5e:	f2 ae                	repnz scas %es:(%rdi),%al
  401f60:	48 89 ca             	mov    %rcx,%rdx
  401f63:	48 f7 d2             	not    %rdx
  401f66:	48 8d 52 ff          	lea    -0x1(%rdx),%rdx
  401f6a:	4c 89 ee             	mov    %r13,%rsi
  401f6d:	89 df                	mov    %ebx,%edi
  401f6f:	e8 d6 fb ff ff       	callq  401b4a <rio_writen>
  401f74:	48 83 c4 10          	add    $0x10,%rsp
  401f78:	48 85 c0             	test   %rax,%rax
  401f7b:	0f 88 55 03 00 00    	js     4022d6 <submitr+0x51e>
  401f81:	89 de                	mov    %ebx,%esi
  401f83:	48 8d bd b0 df ff ff 	lea    -0x2050(%rbp),%rdi
  401f8a:	e8 7b fb ff ff       	callq  401b0a <rio_readinitb>
  401f8f:	ba 00 20 00 00       	mov    $0x2000,%edx
  401f94:	48 8d b5 b0 bf ff ff 	lea    -0x4050(%rbp),%rsi
  401f9b:	48 8d bd b0 df ff ff 	lea    -0x2050(%rbp),%rdi
  401fa2:	e8 8c fd ff ff       	callq  401d33 <rio_readlineb>
  401fa7:	48 85 c0             	test   %rax,%rax
  401faa:	0f 8e 8a 03 00 00    	jle    40233a <submitr+0x582>
  401fb0:	4c 8d 85 a0 5f ff ff 	lea    -0xa060(%rbp),%r8
  401fb7:	48 8d 8d ac 7f ff ff 	lea    -0x8054(%rbp),%rcx
  401fbe:	48 8d 95 b0 7f ff ff 	lea    -0x8050(%rbp),%rdx
  401fc5:	be cf 34 40 00       	mov    $0x4034cf,%esi
  401fca:	48 8d bd b0 bf ff ff 	lea    -0x4050(%rbp),%rdi
  401fd1:	b8 00 00 00 00       	mov    $0x0,%eax
  401fd6:	e8 45 f1 ff ff       	callq  401120 <__isoc99_sscanf@plt>
  401fdb:	44 8b 85 ac 7f ff ff 	mov    -0x8054(%rbp),%r8d
  401fe2:	41 81 f8 c8 00 00 00 	cmp    $0xc8,%r8d
  401fe9:	0f 85 c6 03 00 00    	jne    4023b5 <submitr+0x5fd>
  401fef:	48 8d b5 b0 bf ff ff 	lea    -0x4050(%rbp),%rsi
  401ff6:	bf e0 34 40 00       	mov    $0x4034e0,%edi
  401ffb:	b9 03 00 00 00       	mov    $0x3,%ecx
  402000:	f3 a6                	repz cmpsb %es:(%rdi),%ds:(%rsi)
  402002:	0f 97 c0             	seta   %al
  402005:	1c 00                	sbb    $0x0,%al
  402007:	84 c0                	test   %al,%al
  402009:	0f 84 d9 03 00 00    	je     4023e8 <submitr+0x630>
  40200f:	ba 00 20 00 00       	mov    $0x2000,%edx
  402014:	48 8d b5 b0 bf ff ff 	lea    -0x4050(%rbp),%rsi
  40201b:	48 8d bd b0 df ff ff 	lea    -0x2050(%rbp),%rdi
  402022:	e8 0c fd ff ff       	callq  401d33 <rio_readlineb>
  402027:	48 85 c0             	test   %rax,%rax
  40202a:	7f c3                	jg     401fef <submitr+0x237>
  40202c:	48 b8 45 72 72 6f 72 	movabs $0x43203a726f727245,%rax
  402033:	3a 20 43 
  402036:	48 ba 6c 69 65 6e 74 	movabs $0x6e7520746e65696c,%rdx
  40203d:	20 75 6e 
  402040:	49 89 04 24          	mov    %rax,(%r12)
  402044:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  402049:	48 b8 61 62 6c 65 20 	movabs $0x206f7420656c6261,%rax
  402050:	74 6f 20 
  402053:	48 ba 72 65 61 64 20 	movabs $0x6165682064616572,%rdx
  40205a:	68 65 61 
  40205d:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  402062:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  402067:	48 b8 64 65 72 73 20 	movabs $0x6f72662073726564,%rax
  40206e:	66 72 6f 
  402071:	48 ba 6d 20 73 65 72 	movabs $0x726576726573206d,%rdx
  402078:	76 65 72 
  40207b:	49 89 44 24 20       	mov    %rax,0x20(%r12)
  402080:	49 89 54 24 28       	mov    %rdx,0x28(%r12)
  402085:	41 c6 44 24 30 00    	movb   $0x0,0x30(%r12)
  40208b:	89 df                	mov    %ebx,%edi
  40208d:	e8 fe ef ff ff       	callq  401090 <close@plt>
  402092:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  402097:	e9 9e 03 00 00       	jmpq   40243a <submitr+0x682>
  40209c:	48 b8 45 72 72 6f 72 	movabs $0x43203a726f727245,%rax
  4020a3:	3a 20 43 
  4020a6:	48 ba 6c 69 65 6e 74 	movabs $0x6e7520746e65696c,%rdx
  4020ad:	20 75 6e 
  4020b0:	49 89 04 24          	mov    %rax,(%r12)
  4020b4:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  4020b9:	48 b8 61 62 6c 65 20 	movabs $0x206f7420656c6261,%rax
  4020c0:	74 6f 20 
  4020c3:	48 ba 63 72 65 61 74 	movabs $0x7320657461657263,%rdx
  4020ca:	65 20 73 
  4020cd:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  4020d2:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  4020d7:	41 c7 44 24 20 6f 63 	movl   $0x656b636f,0x20(%r12)
  4020de:	6b 65 
  4020e0:	66 41 c7 44 24 24 74 	movw   $0x74,0x24(%r12)
  4020e7:	00 
  4020e8:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4020ed:	e9 48 03 00 00       	jmpq   40243a <submitr+0x682>
  4020f2:	48 b8 45 72 72 6f 72 	movabs $0x44203a726f727245,%rax
  4020f9:	3a 20 44 
  4020fc:	48 ba 4e 53 20 69 73 	movabs $0x6e7520736920534e,%rdx
  402103:	20 75 6e 
  402106:	49 89 04 24          	mov    %rax,(%r12)
  40210a:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  40210f:	48 b8 61 62 6c 65 20 	movabs $0x206f7420656c6261,%rax
  402116:	74 6f 20 
  402119:	48 ba 72 65 73 6f 6c 	movabs $0x2065766c6f736572,%rdx
  402120:	76 65 20 
  402123:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  402128:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  40212d:	48 b8 73 65 72 76 65 	movabs $0x6120726576726573,%rax
  402134:	72 20 61 
  402137:	49 89 44 24 20       	mov    %rax,0x20(%r12)
  40213c:	41 c7 44 24 28 64 64 	movl   $0x65726464,0x28(%r12)
  402143:	72 65 
  402145:	66 41 c7 44 24 2c 73 	movw   $0x7373,0x2c(%r12)
  40214c:	73 
  40214d:	41 c6 44 24 2e 00    	movb   $0x0,0x2e(%r12)
  402153:	89 df                	mov    %ebx,%edi
  402155:	e8 36 ef ff ff       	callq  401090 <close@plt>
  40215a:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  40215f:	e9 d6 02 00 00       	jmpq   40243a <submitr+0x682>
  402164:	48 b8 45 72 72 6f 72 	movabs $0x55203a726f727245,%rax
  40216b:	3a 20 55 
  40216e:	48 ba 6e 61 62 6c 65 	movabs $0x6f7420656c62616e,%rdx
  402175:	20 74 6f 
  402178:	49 89 04 24          	mov    %rax,(%r12)
  40217c:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  402181:	48 b8 20 63 6f 6e 6e 	movabs $0x7463656e6e6f6320,%rax
  402188:	65 63 74 
  40218b:	48 ba 20 74 6f 20 74 	movabs $0x20656874206f7420,%rdx
  402192:	68 65 20 
  402195:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  40219a:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  40219f:	41 c7 44 24 20 73 65 	movl   $0x76726573,0x20(%r12)
  4021a6:	72 76 
  4021a8:	66 41 c7 44 24 24 65 	movw   $0x7265,0x24(%r12)
  4021af:	72 
  4021b0:	41 c6 44 24 26 00    	movb   $0x0,0x26(%r12)
  4021b6:	89 df                	mov    %ebx,%edi
  4021b8:	e8 d3 ee ff ff       	callq  401090 <close@plt>
  4021bd:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4021c2:	e9 73 02 00 00       	jmpq   40243a <submitr+0x682>
  4021c7:	48 b8 45 72 72 6f 72 	movabs $0x52203a726f727245,%rax
  4021ce:	3a 20 52 
  4021d1:	48 ba 65 73 75 6c 74 	movabs $0x747320746c757365,%rdx
  4021d8:	20 73 74 
  4021db:	49 89 04 24          	mov    %rax,(%r12)
  4021df:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  4021e4:	48 b8 72 69 6e 67 20 	movabs $0x6f6f7420676e6972,%rax
  4021eb:	74 6f 6f 
  4021ee:	48 ba 20 6c 61 72 67 	movabs $0x202e656772616c20,%rdx
  4021f5:	65 2e 20 
  4021f8:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  4021fd:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  402202:	48 b8 49 6e 63 72 65 	movabs $0x6573616572636e49,%rax
  402209:	61 73 65 
  40220c:	48 ba 20 53 55 42 4d 	movabs $0x5254494d42555320,%rdx
  402213:	49 54 52 
  402216:	49 89 44 24 20       	mov    %rax,0x20(%r12)
  40221b:	49 89 54 24 28       	mov    %rdx,0x28(%r12)
  402220:	48 b8 5f 4d 41 58 42 	movabs $0x46554258414d5f,%rax
  402227:	55 46 00 
  40222a:	49 89 44 24 30       	mov    %rax,0x30(%r12)
  40222f:	89 df                	mov    %ebx,%edi
  402231:	e8 5a ee ff ff       	callq  401090 <close@plt>
  402236:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  40223b:	e9 fa 01 00 00       	jmpq   40243a <submitr+0x682>
  402240:	48 b8 45 72 72 6f 72 	movabs $0x52203a726f727245,%rax
  402247:	3a 20 52 
  40224a:	48 ba 65 73 75 6c 74 	movabs $0x747320746c757365,%rdx
  402251:	20 73 74 
  402254:	49 89 04 24          	mov    %rax,(%r12)
  402258:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  40225d:	48 b8 72 69 6e 67 20 	movabs $0x6e6f6320676e6972,%rax
  402264:	63 6f 6e 
  402267:	48 ba 74 61 69 6e 73 	movabs $0x6e6120736e696174,%rdx
  40226e:	20 61 6e 
  402271:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  402276:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  40227b:	48 b8 20 69 6c 6c 65 	movabs $0x6c6167656c6c6920,%rax
  402282:	67 61 6c 
  402285:	48 ba 20 6f 72 20 75 	movabs $0x72706e7520726f20,%rdx
  40228c:	6e 70 72 
  40228f:	49 89 44 24 20       	mov    %rax,0x20(%r12)
  402294:	49 89 54 24 28       	mov    %rdx,0x28(%r12)
  402299:	48 b8 69 6e 74 61 62 	movabs $0x20656c6261746e69,%rax
  4022a0:	6c 65 20 
  4022a3:	48 ba 63 68 61 72 61 	movabs $0x6574636172616863,%rdx
  4022aa:	63 74 65 
  4022ad:	49 89 44 24 30       	mov    %rax,0x30(%r12)
  4022b2:	49 89 54 24 38       	mov    %rdx,0x38(%r12)
  4022b7:	66 41 c7 44 24 40 72 	movw   $0x2e72,0x40(%r12)
  4022be:	2e 
  4022bf:	41 c6 44 24 42 00    	movb   $0x0,0x42(%r12)
  4022c5:	89 df                	mov    %ebx,%edi
  4022c7:	e8 c4 ed ff ff       	callq  401090 <close@plt>
  4022cc:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4022d1:	e9 64 01 00 00       	jmpq   40243a <submitr+0x682>
  4022d6:	48 b8 45 72 72 6f 72 	movabs $0x43203a726f727245,%rax
  4022dd:	3a 20 43 
  4022e0:	48 ba 6c 69 65 6e 74 	movabs $0x6e7520746e65696c,%rdx
  4022e7:	20 75 6e 
  4022ea:	49 89 04 24          	mov    %rax,(%r12)
  4022ee:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  4022f3:	48 b8 61 62 6c 65 20 	movabs $0x206f7420656c6261,%rax
  4022fa:	74 6f 20 
  4022fd:	48 ba 77 72 69 74 65 	movabs $0x6f74206574697277,%rdx
  402304:	20 74 6f 
  402307:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  40230c:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  402311:	48 b8 20 74 68 65 20 	movabs $0x7265732065687420,%rax
  402318:	73 65 72 
  40231b:	49 89 44 24 20       	mov    %rax,0x20(%r12)
  402320:	41 c7 44 24 28 76 65 	movl   $0x726576,0x28(%r12)
  402327:	72 00 
  402329:	89 df                	mov    %ebx,%edi
  40232b:	e8 60 ed ff ff       	callq  401090 <close@plt>
  402330:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  402335:	e9 00 01 00 00       	jmpq   40243a <submitr+0x682>
  40233a:	48 b8 45 72 72 6f 72 	movabs $0x43203a726f727245,%rax
  402341:	3a 20 43 
  402344:	48 ba 6c 69 65 6e 74 	movabs $0x6e7520746e65696c,%rdx
  40234b:	20 75 6e 
  40234e:	49 89 04 24          	mov    %rax,(%r12)
  402352:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  402357:	48 b8 61 62 6c 65 20 	movabs $0x206f7420656c6261,%rax
  40235e:	74 6f 20 
  402361:	48 ba 72 65 61 64 20 	movabs $0x7269662064616572,%rdx
  402368:	66 69 72 
  40236b:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  402370:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  402375:	48 b8 73 74 20 68 65 	movabs $0x6564616568207473,%rax
  40237c:	61 64 65 
  40237f:	48 ba 72 20 66 72 6f 	movabs $0x73206d6f72662072,%rdx
  402386:	6d 20 73 
  402389:	49 89 44 24 20       	mov    %rax,0x20(%r12)
  40238e:	49 89 54 24 28       	mov    %rdx,0x28(%r12)
  402393:	41 c7 44 24 30 65 72 	movl   $0x65767265,0x30(%r12)
  40239a:	76 65 
  40239c:	66 41 c7 44 24 34 72 	movw   $0x72,0x34(%r12)
  4023a3:	00 
  4023a4:	89 df                	mov    %ebx,%edi
  4023a6:	e8 e5 ec ff ff       	callq  401090 <close@plt>
  4023ab:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4023b0:	e9 85 00 00 00       	jmpq   40243a <submitr+0x682>
  4023b5:	4c 8d 8d a0 5f ff ff 	lea    -0xa060(%rbp),%r9
  4023bc:	b9 70 34 40 00       	mov    $0x403470,%ecx
  4023c1:	48 c7 c2 ff ff ff ff 	mov    $0xffffffffffffffff,%rdx
  4023c8:	be 01 00 00 00       	mov    $0x1,%esi
  4023cd:	4c 89 e7             	mov    %r12,%rdi
  4023d0:	b8 00 00 00 00       	mov    $0x0,%eax
  4023d5:	e8 c6 ed ff ff       	callq  4011a0 <__sprintf_chk@plt>
  4023da:	89 df                	mov    %ebx,%edi
  4023dc:	e8 af ec ff ff       	callq  401090 <close@plt>
  4023e1:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4023e6:	eb 52                	jmp    40243a <submitr+0x682>
  4023e8:	ba 00 20 00 00       	mov    $0x2000,%edx
  4023ed:	48 8d b5 b0 bf ff ff 	lea    -0x4050(%rbp),%rsi
  4023f4:	48 8d bd b0 df ff ff 	lea    -0x2050(%rbp),%rdi
  4023fb:	e8 33 f9 ff ff       	callq  401d33 <rio_readlineb>
  402400:	48 85 c0             	test   %rax,%rax
  402403:	7e 44                	jle    402449 <submitr+0x691>
  402405:	48 8d b5 b0 bf ff ff 	lea    -0x4050(%rbp),%rsi
  40240c:	4c 89 e7             	mov    %r12,%rdi
  40240f:	e8 3c ec ff ff       	callq  401050 <strcpy@plt>
  402414:	89 df                	mov    %ebx,%edi
  402416:	e8 75 ec ff ff       	callq  401090 <close@plt>
  40241b:	bf e3 34 40 00       	mov    $0x4034e3,%edi
  402420:	b9 03 00 00 00       	mov    $0x3,%ecx
  402425:	4c 89 e6             	mov    %r12,%rsi
  402428:	f3 a6                	repz cmpsb %es:(%rdi),%ds:(%rsi)
  40242a:	0f 97 c0             	seta   %al
  40242d:	1c 00                	sbb    $0x0,%al
  40242f:	0f be c0             	movsbl %al,%eax
  402432:	85 c0                	test   %eax,%eax
  402434:	0f 85 88 00 00 00    	jne    4024c2 <submitr+0x70a>
  40243a:	48 8d 65 d8          	lea    -0x28(%rbp),%rsp
  40243e:	5b                   	pop    %rbx
  40243f:	41 5c                	pop    %r12
  402441:	41 5d                	pop    %r13
  402443:	41 5e                	pop    %r14
  402445:	41 5f                	pop    %r15
  402447:	5d                   	pop    %rbp
  402448:	c3                   	retq   
  402449:	48 b8 45 72 72 6f 72 	movabs $0x43203a726f727245,%rax
  402450:	3a 20 43 
  402453:	48 ba 6c 69 65 6e 74 	movabs $0x6e7520746e65696c,%rdx
  40245a:	20 75 6e 
  40245d:	49 89 04 24          	mov    %rax,(%r12)
  402461:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  402466:	48 b8 61 62 6c 65 20 	movabs $0x206f7420656c6261,%rax
  40246d:	74 6f 20 
  402470:	48 ba 72 65 61 64 20 	movabs $0x6174732064616572,%rdx
  402477:	73 74 61 
  40247a:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  40247f:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  402484:	48 b8 74 75 73 20 6d 	movabs $0x7373656d20737574,%rax
  40248b:	65 73 73 
  40248e:	48 ba 61 67 65 20 66 	movabs $0x6d6f726620656761,%rdx
  402495:	72 6f 6d 
  402498:	49 89 44 24 20       	mov    %rax,0x20(%r12)
  40249d:	49 89 54 24 28       	mov    %rdx,0x28(%r12)
  4024a2:	48 b8 20 73 65 72 76 	movabs $0x72657672657320,%rax
  4024a9:	65 72 00 
  4024ac:	49 89 44 24 30       	mov    %rax,0x30(%r12)
  4024b1:	89 df                	mov    %ebx,%edi
  4024b3:	e8 d8 eb ff ff       	callq  401090 <close@plt>
  4024b8:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4024bd:	e9 78 ff ff ff       	jmpq   40243a <submitr+0x682>
  4024c2:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4024c7:	e9 6e ff ff ff       	jmpq   40243a <submitr+0x682>

00000000004024cc <init_timeout>:
  4024cc:	55                   	push   %rbp
  4024cd:	48 89 e5             	mov    %rsp,%rbp
  4024d0:	53                   	push   %rbx
  4024d1:	48 83 ec 08          	sub    $0x8,%rsp
  4024d5:	85 ff                	test   %edi,%edi
  4024d7:	74 1a                	je     4024f3 <init_timeout+0x27>
  4024d9:	89 fb                	mov    %edi,%ebx
  4024db:	78 1d                	js     4024fa <init_timeout+0x2e>
  4024dd:	be 1c 1b 40 00       	mov    $0x401b1c,%esi
  4024e2:	bf 0e 00 00 00       	mov    $0xe,%edi
  4024e7:	e8 d4 eb ff ff       	callq  4010c0 <signal@plt>
  4024ec:	89 df                	mov    %ebx,%edi
  4024ee:	e8 8d eb ff ff       	callq  401080 <alarm@plt>
  4024f3:	48 83 c4 08          	add    $0x8,%rsp
  4024f7:	5b                   	pop    %rbx
  4024f8:	5d                   	pop    %rbp
  4024f9:	c3                   	retq   
  4024fa:	bb 00 00 00 00       	mov    $0x0,%ebx
  4024ff:	eb dc                	jmp    4024dd <init_timeout+0x11>

0000000000402501 <init_driver>:
  402501:	55                   	push   %rbp
  402502:	48 89 e5             	mov    %rsp,%rbp
  402505:	41 54                	push   %r12
  402507:	53                   	push   %rbx
  402508:	48 83 ec 10          	sub    $0x10,%rsp
  40250c:	49 89 fc             	mov    %rdi,%r12
  40250f:	be 01 00 00 00       	mov    $0x1,%esi
  402514:	bf 0d 00 00 00       	mov    $0xd,%edi
  402519:	e8 a2 eb ff ff       	callq  4010c0 <signal@plt>
  40251e:	be 01 00 00 00       	mov    $0x1,%esi
  402523:	bf 1d 00 00 00       	mov    $0x1d,%edi
  402528:	e8 93 eb ff ff       	callq  4010c0 <signal@plt>
  40252d:	be 01 00 00 00       	mov    $0x1,%esi
  402532:	bf 1d 00 00 00       	mov    $0x1d,%edi
  402537:	e8 84 eb ff ff       	callq  4010c0 <signal@plt>
  40253c:	ba 00 00 00 00       	mov    $0x0,%edx
  402541:	be 01 00 00 00       	mov    $0x1,%esi
  402546:	bf 02 00 00 00       	mov    $0x2,%edi
  40254b:	e8 60 ec ff ff       	callq  4011b0 <socket@plt>
  402550:	85 c0                	test   %eax,%eax
  402552:	0f 88 8d 00 00 00    	js     4025e5 <init_driver+0xe4>
  402558:	89 c3                	mov    %eax,%ebx
  40255a:	bf e6 34 40 00       	mov    $0x4034e6,%edi
  40255f:	e8 6c eb ff ff       	callq  4010d0 <gethostbyname@plt>
  402564:	48 85 c0             	test   %rax,%rax
  402567:	0f 84 cb 00 00 00    	je     402638 <init_driver+0x137>
  40256d:	48 8d 4d e0          	lea    -0x20(%rbp),%rcx
  402571:	48 c7 45 e2 00 00 00 	movq   $0x0,-0x1e(%rbp)
  402578:	00 
  402579:	c7 45 ea 00 00 00 00 	movl   $0x0,-0x16(%rbp)
  402580:	66 c7 45 ee 00 00    	movw   $0x0,-0x12(%rbp)
  402586:	66 c7 45 e0 02 00    	movw   $0x2,-0x20(%rbp)
  40258c:	48 63 50 14          	movslq 0x14(%rax),%rdx
  402590:	48 8b 40 18          	mov    0x18(%rax),%rax
  402594:	48 8b 30             	mov    (%rax),%rsi
  402597:	48 8d 79 04          	lea    0x4(%rcx),%rdi
  40259b:	b9 0c 00 00 00       	mov    $0xc,%ecx
  4025a0:	e8 3b eb ff ff       	callq  4010e0 <__memmove_chk@plt>
  4025a5:	66 c7 45 e2 3b 6e    	movw   $0x6e3b,-0x1e(%rbp)
  4025ab:	ba 10 00 00 00       	mov    $0x10,%edx
  4025b0:	48 8d 75 e0          	lea    -0x20(%rbp),%rsi
  4025b4:	89 df                	mov    %ebx,%edi
  4025b6:	e8 a5 eb ff ff       	callq  401160 <connect@plt>
  4025bb:	85 c0                	test   %eax,%eax
  4025bd:	0f 88 e7 00 00 00    	js     4026aa <init_driver+0x1a9>
  4025c3:	89 df                	mov    %ebx,%edi
  4025c5:	e8 c6 ea ff ff       	callq  401090 <close@plt>
  4025ca:	66 41 c7 04 24 4f 4b 	movw   $0x4b4f,(%r12)
  4025d1:	41 c6 44 24 02 00    	movb   $0x0,0x2(%r12)
  4025d7:	b8 00 00 00 00       	mov    $0x0,%eax
  4025dc:	48 83 c4 10          	add    $0x10,%rsp
  4025e0:	5b                   	pop    %rbx
  4025e1:	41 5c                	pop    %r12
  4025e3:	5d                   	pop    %rbp
  4025e4:	c3                   	retq   
  4025e5:	48 b8 45 72 72 6f 72 	movabs $0x43203a726f727245,%rax
  4025ec:	3a 20 43 
  4025ef:	48 ba 6c 69 65 6e 74 	movabs $0x6e7520746e65696c,%rdx
  4025f6:	20 75 6e 
  4025f9:	49 89 04 24          	mov    %rax,(%r12)
  4025fd:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  402602:	48 b8 61 62 6c 65 20 	movabs $0x206f7420656c6261,%rax
  402609:	74 6f 20 
  40260c:	48 ba 63 72 65 61 74 	movabs $0x7320657461657263,%rdx
  402613:	65 20 73 
  402616:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  40261b:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  402620:	41 c7 44 24 20 6f 63 	movl   $0x656b636f,0x20(%r12)
  402627:	6b 65 
  402629:	66 41 c7 44 24 24 74 	movw   $0x74,0x24(%r12)
  402630:	00 
  402631:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  402636:	eb a4                	jmp    4025dc <init_driver+0xdb>
  402638:	48 b8 45 72 72 6f 72 	movabs $0x44203a726f727245,%rax
  40263f:	3a 20 44 
  402642:	48 ba 4e 53 20 69 73 	movabs $0x6e7520736920534e,%rdx
  402649:	20 75 6e 
  40264c:	49 89 04 24          	mov    %rax,(%r12)
  402650:	49 89 54 24 08       	mov    %rdx,0x8(%r12)
  402655:	48 b8 61 62 6c 65 20 	movabs $0x206f7420656c6261,%rax
  40265c:	74 6f 20 
  40265f:	48 ba 72 65 73 6f 6c 	movabs $0x2065766c6f736572,%rdx
  402666:	76 65 20 
  402669:	49 89 44 24 10       	mov    %rax,0x10(%r12)
  40266e:	49 89 54 24 18       	mov    %rdx,0x18(%r12)
  402673:	48 b8 73 65 72 76 65 	movabs $0x6120726576726573,%rax
  40267a:	72 20 61 
  40267d:	49 89 44 24 20       	mov    %rax,0x20(%r12)
  402682:	41 c7 44 24 28 64 64 	movl   $0x65726464,0x28(%r12)
  402689:	72 65 
  40268b:	66 41 c7 44 24 2c 73 	movw   $0x7373,0x2c(%r12)
  402692:	73 
  402693:	41 c6 44 24 2e 00    	movb   $0x0,0x2e(%r12)
  402699:	89 df                	mov    %ebx,%edi
  40269b:	e8 f0 e9 ff ff       	callq  401090 <close@plt>
  4026a0:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4026a5:	e9 32 ff ff ff       	jmpq   4025dc <init_driver+0xdb>
  4026aa:	41 b8 e6 34 40 00    	mov    $0x4034e6,%r8d
  4026b0:	b9 a0 34 40 00       	mov    $0x4034a0,%ecx
  4026b5:	48 c7 c2 ff ff ff ff 	mov    $0xffffffffffffffff,%rdx
  4026bc:	be 01 00 00 00       	mov    $0x1,%esi
  4026c1:	4c 89 e7             	mov    %r12,%rdi
  4026c4:	b8 00 00 00 00       	mov    $0x0,%eax
  4026c9:	e8 d2 ea ff ff       	callq  4011a0 <__sprintf_chk@plt>
  4026ce:	89 df                	mov    %ebx,%edi
  4026d0:	e8 bb e9 ff ff       	callq  401090 <close@plt>
  4026d5:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
  4026da:	e9 fd fe ff ff       	jmpq   4025dc <init_driver+0xdb>

00000000004026df <driver_post>:
  4026df:	55                   	push   %rbp
  4026e0:	48 89 e5             	mov    %rsp,%rbp
  4026e3:	53                   	push   %rbx
  4026e4:	48 83 ec 08          	sub    $0x8,%rsp
  4026e8:	48 89 cb             	mov    %rcx,%rbx
  4026eb:	85 d2                	test   %edx,%edx
  4026ed:	75 1b                	jne    40270a <driver_post+0x2b>
  4026ef:	48 85 ff             	test   %rdi,%rdi
  4026f2:	74 05                	je     4026f9 <driver_post+0x1a>
  4026f4:	80 3f 00             	cmpb   $0x0,(%rdi)
  4026f7:	75 38                	jne    402731 <driver_post+0x52>
  4026f9:	66 c7 03 4f 4b       	movw   $0x4b4f,(%rbx)
  4026fe:	c6 43 02 00          	movb   $0x0,0x2(%rbx)
  402702:	89 d0                	mov    %edx,%eax
  402704:	48 8b 5d f8          	mov    -0x8(%rbp),%rbx
  402708:	c9                   	leaveq 
  402709:	c3                   	retq   
  40270a:	48 89 f2             	mov    %rsi,%rdx
  40270d:	be f0 34 40 00       	mov    $0x4034f0,%esi
  402712:	bf 01 00 00 00       	mov    $0x1,%edi
  402717:	b8 00 00 00 00       	mov    $0x0,%eax
  40271c:	e8 0f ea ff ff       	callq  401130 <__printf_chk@plt>
  402721:	66 c7 03 4f 4b       	movw   $0x4b4f,(%rbx)
  402726:	c6 43 02 00          	movb   $0x0,0x2(%rbx)
  40272a:	b8 00 00 00 00       	mov    $0x0,%eax
  40272f:	eb d3                	jmp    402704 <driver_post+0x25>
  402731:	48 83 ec 08          	sub    $0x8,%rsp
  402735:	51                   	push   %rcx
  402736:	49 89 f1             	mov    %rsi,%r9
  402739:	41 b8 07 35 40 00    	mov    $0x403507,%r8d
  40273f:	48 89 f9             	mov    %rdi,%rcx
  402742:	ba 12 35 40 00       	mov    $0x403512,%edx
  402747:	be 6e 3b 00 00       	mov    $0x3b6e,%esi
  40274c:	bf e6 34 40 00       	mov    $0x4034e6,%edi
  402751:	e8 62 f6 ff ff       	callq  401db8 <submitr>
  402756:	48 83 c4 10          	add    $0x10,%rsp
  40275a:	eb a8                	jmp    402704 <driver_post+0x25>
  40275c:	0f 1f 40 00          	nopl   0x0(%rax)

0000000000402760 <__libc_csu_init>:
  402760:	41 57                	push   %r15
  402762:	49 89 d7             	mov    %rdx,%r15
  402765:	41 56                	push   %r14
  402767:	49 89 f6             	mov    %rsi,%r14
  40276a:	41 55                	push   %r13
  40276c:	41 89 fd             	mov    %edi,%r13d
  40276f:	41 54                	push   %r12
  402771:	4c 8d 25 98 26 00 00 	lea    0x2698(%rip),%r12        # 404e10 <__frame_dummy_init_array_entry>
  402778:	55                   	push   %rbp
  402779:	48 8d 2d 98 26 00 00 	lea    0x2698(%rip),%rbp        # 404e18 <__init_array_end>
  402780:	53                   	push   %rbx
  402781:	4c 29 e5             	sub    %r12,%rbp
  402784:	48 83 ec 08          	sub    $0x8,%rsp
  402788:	e8 73 e8 ff ff       	callq  401000 <_init>
  40278d:	48 c1 fd 03          	sar    $0x3,%rbp
  402791:	74 1b                	je     4027ae <__libc_csu_init+0x4e>
  402793:	31 db                	xor    %ebx,%ebx
  402795:	0f 1f 00             	nopl   (%rax)
  402798:	4c 89 fa             	mov    %r15,%rdx
  40279b:	4c 89 f6             	mov    %r14,%rsi
  40279e:	44 89 ef             	mov    %r13d,%edi
  4027a1:	41 ff 14 dc          	callq  *(%r12,%rbx,8)
  4027a5:	48 83 c3 01          	add    $0x1,%rbx
  4027a9:	48 39 dd             	cmp    %rbx,%rbp
  4027ac:	75 ea                	jne    402798 <__libc_csu_init+0x38>
  4027ae:	48 83 c4 08          	add    $0x8,%rsp
  4027b2:	5b                   	pop    %rbx
  4027b3:	5d                   	pop    %rbp
  4027b4:	41 5c                	pop    %r12
  4027b6:	41 5d                	pop    %r13
  4027b8:	41 5e                	pop    %r14
  4027ba:	41 5f                	pop    %r15
  4027bc:	c3                   	retq   
  4027bd:	0f 1f 00             	nopl   (%rax)

00000000004027c0 <__libc_csu_fini>:
  4027c0:	c3                   	retq   

Disassembly of section .fini:

00000000004027c4 <_fini>:
  4027c4:	48 83 ec 08          	sub    $0x8,%rsp
  4027c8:	48 83 c4 08          	add    $0x8,%rsp
  4027cc:	c3                   	retq   
