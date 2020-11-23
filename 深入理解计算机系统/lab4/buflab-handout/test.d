
test：     文件格式 elf32-i386


Disassembly of section .init:

00001000 <_init>:
    1000:	f3 0f 1e fb          	endbr32 
    1004:	53                   	push   %ebx
    1005:	83 ec 08             	sub    $0x8,%esp
    1008:	e8 83 00 00 00       	call   1090 <__x86.get_pc_thunk.bx>
    100d:	81 c3 f3 2f 00 00    	add    $0x2ff3,%ebx
    1013:	8b 83 f4 ff ff ff    	mov    -0xc(%ebx),%eax
    1019:	85 c0                	test   %eax,%eax
    101b:	74 02                	je     101f <_init+0x1f>
    101d:	ff d0                	call   *%eax
    101f:	83 c4 08             	add    $0x8,%esp
    1022:	5b                   	pop    %ebx
    1023:	c3                   	ret    

Disassembly of section .plt:

00001030 <.plt>:
    1030:	ff b3 04 00 00 00    	pushl  0x4(%ebx)
    1036:	ff a3 08 00 00 00    	jmp    *0x8(%ebx)
    103c:	00 00                	add    %al,(%eax)
	...

00001040 <__libc_start_main@plt>:
    1040:	ff a3 0c 00 00 00    	jmp    *0xc(%ebx)
    1046:	68 00 00 00 00       	push   $0x0
    104b:	e9 e0 ff ff ff       	jmp    1030 <.plt>

Disassembly of section .text:

00001050 <_start>:
    1050:	f3 0f 1e fb          	endbr32 
    1054:	31 ed                	xor    %ebp,%ebp
    1056:	5e                   	pop    %esi
    1057:	89 e1                	mov    %esp,%ecx
    1059:	83 e4 f0             	and    $0xfffffff0,%esp
    105c:	50                   	push   %eax
    105d:	54                   	push   %esp
    105e:	52                   	push   %edx
    105f:	e8 22 00 00 00       	call   1086 <_start+0x36>
    1064:	81 c3 9c 2f 00 00    	add    $0x2f9c,%ebx
    106a:	8d 83 20 d2 ff ff    	lea    -0x2de0(%ebx),%eax
    1070:	50                   	push   %eax
    1071:	8d 83 b0 d1 ff ff    	lea    -0x2e50(%ebx),%eax
    1077:	50                   	push   %eax
    1078:	51                   	push   %ecx
    1079:	56                   	push   %esi
    107a:	ff b3 f8 ff ff ff    	pushl  -0x8(%ebx)
    1080:	e8 bb ff ff ff       	call   1040 <__libc_start_main@plt>
    1085:	f4                   	hlt    
    1086:	8b 1c 24             	mov    (%esp),%ebx
    1089:	c3                   	ret    
    108a:	66 90                	xchg   %ax,%ax
    108c:	66 90                	xchg   %ax,%ax
    108e:	66 90                	xchg   %ax,%ax

00001090 <__x86.get_pc_thunk.bx>:
    1090:	8b 1c 24             	mov    (%esp),%ebx
    1093:	c3                   	ret    
    1094:	66 90                	xchg   %ax,%ax
    1096:	66 90                	xchg   %ax,%ax
    1098:	66 90                	xchg   %ax,%ax
    109a:	66 90                	xchg   %ax,%ax
    109c:	66 90                	xchg   %ax,%ax
    109e:	66 90                	xchg   %ax,%ax

000010a0 <deregister_tm_clones>:
    10a0:	e8 e4 00 00 00       	call   1189 <__x86.get_pc_thunk.dx>
    10a5:	81 c2 5b 2f 00 00    	add    $0x2f5b,%edx
    10ab:	8d 8a 18 00 00 00    	lea    0x18(%edx),%ecx
    10b1:	8d 82 18 00 00 00    	lea    0x18(%edx),%eax
    10b7:	39 c8                	cmp    %ecx,%eax
    10b9:	74 1d                	je     10d8 <deregister_tm_clones+0x38>
    10bb:	8b 82 ec ff ff ff    	mov    -0x14(%edx),%eax
    10c1:	85 c0                	test   %eax,%eax
    10c3:	74 13                	je     10d8 <deregister_tm_clones+0x38>
    10c5:	55                   	push   %ebp
    10c6:	89 e5                	mov    %esp,%ebp
    10c8:	83 ec 14             	sub    $0x14,%esp
    10cb:	51                   	push   %ecx
    10cc:	ff d0                	call   *%eax
    10ce:	83 c4 10             	add    $0x10,%esp
    10d1:	c9                   	leave  
    10d2:	c3                   	ret    
    10d3:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
    10d7:	90                   	nop
    10d8:	c3                   	ret    
    10d9:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi

000010e0 <register_tm_clones>:
    10e0:	e8 a4 00 00 00       	call   1189 <__x86.get_pc_thunk.dx>
    10e5:	81 c2 1b 2f 00 00    	add    $0x2f1b,%edx
    10eb:	55                   	push   %ebp
    10ec:	89 e5                	mov    %esp,%ebp
    10ee:	53                   	push   %ebx
    10ef:	8d 8a 18 00 00 00    	lea    0x18(%edx),%ecx
    10f5:	8d 82 18 00 00 00    	lea    0x18(%edx),%eax
    10fb:	83 ec 04             	sub    $0x4,%esp
    10fe:	29 c8                	sub    %ecx,%eax
    1100:	89 c3                	mov    %eax,%ebx
    1102:	c1 e8 1f             	shr    $0x1f,%eax
    1105:	c1 fb 02             	sar    $0x2,%ebx
    1108:	01 d8                	add    %ebx,%eax
    110a:	d1 f8                	sar    %eax
    110c:	74 14                	je     1122 <register_tm_clones+0x42>
    110e:	8b 92 fc ff ff ff    	mov    -0x4(%edx),%edx
    1114:	85 d2                	test   %edx,%edx
    1116:	74 0a                	je     1122 <register_tm_clones+0x42>
    1118:	83 ec 08             	sub    $0x8,%esp
    111b:	50                   	push   %eax
    111c:	51                   	push   %ecx
    111d:	ff d2                	call   *%edx
    111f:	83 c4 10             	add    $0x10,%esp
    1122:	8b 5d fc             	mov    -0x4(%ebp),%ebx
    1125:	c9                   	leave  
    1126:	c3                   	ret    
    1127:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
    112e:	66 90                	xchg   %ax,%ax

00001130 <__do_global_dtors_aux>:
    1130:	f3 0f 1e fb          	endbr32 
    1134:	55                   	push   %ebp
    1135:	89 e5                	mov    %esp,%ebp
    1137:	53                   	push   %ebx
    1138:	e8 53 ff ff ff       	call   1090 <__x86.get_pc_thunk.bx>
    113d:	81 c3 c3 2e 00 00    	add    $0x2ec3,%ebx
    1143:	83 ec 04             	sub    $0x4,%esp
    1146:	80 bb 18 00 00 00 00 	cmpb   $0x0,0x18(%ebx)
    114d:	75 28                	jne    1177 <__do_global_dtors_aux+0x47>
    114f:	8b 83 f0 ff ff ff    	mov    -0x10(%ebx),%eax
    1155:	85 c0                	test   %eax,%eax
    1157:	74 12                	je     116b <__do_global_dtors_aux+0x3b>
    1159:	83 ec 0c             	sub    $0xc,%esp
    115c:	ff b3 14 00 00 00    	pushl  0x14(%ebx)
    1162:	ff 93 f0 ff ff ff    	call   *-0x10(%ebx)
    1168:	83 c4 10             	add    $0x10,%esp
    116b:	e8 30 ff ff ff       	call   10a0 <deregister_tm_clones>
    1170:	c6 83 18 00 00 00 01 	movb   $0x1,0x18(%ebx)
    1177:	8b 5d fc             	mov    -0x4(%ebp),%ebx
    117a:	c9                   	leave  
    117b:	c3                   	ret    
    117c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi

00001180 <frame_dummy>:
    1180:	f3 0f 1e fb          	endbr32 
    1184:	e9 57 ff ff ff       	jmp    10e0 <register_tm_clones>

00001189 <__x86.get_pc_thunk.dx>:
    1189:	8b 14 24             	mov    (%esp),%edx
    118c:	c3                   	ret    

0000118d <main>:
    118d:	b8 a7 8c 04 08       	mov    $0x8048ca7,%eax
    1192:	a3 64 38 68 55       	mov    %eax,0x55683864
    1197:	b8 c4 d0 a8 7c       	mov    $0x7ca8d0c4,%eax
    119c:	bd c0 33 68 55       	mov    $0x556833c0,%ebp
    11a1:	e9 01 7b 04 08       	jmp    8048ca7 <_end+0x8044c8b>
    11a6:	66 90                	xchg   %ax,%ax
    11a8:	66 90                	xchg   %ax,%ax
    11aa:	66 90                	xchg   %ax,%ax
    11ac:	66 90                	xchg   %ax,%ax
    11ae:	66 90                	xchg   %ax,%ax

000011b0 <__libc_csu_init>:
    11b0:	f3 0f 1e fb          	endbr32 
    11b4:	55                   	push   %ebp
    11b5:	e8 6b 00 00 00       	call   1225 <__x86.get_pc_thunk.bp>
    11ba:	81 c5 46 2e 00 00    	add    $0x2e46,%ebp
    11c0:	57                   	push   %edi
    11c1:	56                   	push   %esi
    11c2:	53                   	push   %ebx
    11c3:	83 ec 0c             	sub    $0xc,%esp
    11c6:	89 eb                	mov    %ebp,%ebx
    11c8:	8b 7c 24 28          	mov    0x28(%esp),%edi
    11cc:	e8 2f fe ff ff       	call   1000 <_init>
    11d1:	8d 9d f8 fe ff ff    	lea    -0x108(%ebp),%ebx
    11d7:	8d 85 f4 fe ff ff    	lea    -0x10c(%ebp),%eax
    11dd:	29 c3                	sub    %eax,%ebx
    11df:	c1 fb 02             	sar    $0x2,%ebx
    11e2:	74 29                	je     120d <__libc_csu_init+0x5d>
    11e4:	31 f6                	xor    %esi,%esi
    11e6:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
    11ed:	8d 76 00             	lea    0x0(%esi),%esi
    11f0:	83 ec 04             	sub    $0x4,%esp
    11f3:	57                   	push   %edi
    11f4:	ff 74 24 2c          	pushl  0x2c(%esp)
    11f8:	ff 74 24 2c          	pushl  0x2c(%esp)
    11fc:	ff 94 b5 f4 fe ff ff 	call   *-0x10c(%ebp,%esi,4)
    1203:	83 c6 01             	add    $0x1,%esi
    1206:	83 c4 10             	add    $0x10,%esp
    1209:	39 f3                	cmp    %esi,%ebx
    120b:	75 e3                	jne    11f0 <__libc_csu_init+0x40>
    120d:	83 c4 0c             	add    $0xc,%esp
    1210:	5b                   	pop    %ebx
    1211:	5e                   	pop    %esi
    1212:	5f                   	pop    %edi
    1213:	5d                   	pop    %ebp
    1214:	c3                   	ret    
    1215:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
    121c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi

00001220 <__libc_csu_fini>:
    1220:	f3 0f 1e fb          	endbr32 
    1224:	c3                   	ret    

00001225 <__x86.get_pc_thunk.bp>:
    1225:	8b 2c 24             	mov    (%esp),%ebp
    1228:	c3                   	ret    

Disassembly of section .fini:

0000122c <_fini>:
    122c:	f3 0f 1e fb          	endbr32 
    1230:	53                   	push   %ebx
    1231:	83 ec 08             	sub    $0x8,%esp
    1234:	e8 57 fe ff ff       	call   1090 <__x86.get_pc_thunk.bx>
    1239:	81 c3 c7 2d 00 00    	add    $0x2dc7,%ebx
    123f:	83 c4 08             	add    $0x8,%esp
    1242:	5b                   	pop    %ebx
    1243:	c3                   	ret    
