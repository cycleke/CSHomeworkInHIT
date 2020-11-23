
bufbomb：     文件格式 elf32-i386


Disassembly of section .init:

0804881c <_init>:
 804881c:	53                   	push   %ebx
 804881d:	83 ec 08             	sub    $0x8,%esp
 8048820:	e8 cb 02 00 00       	call   8048af0 <__x86.get_pc_thunk.bx>
 8048825:	81 c3 db 47 00 00    	add    $0x47db,%ebx
 804882b:	8b 83 fc ff ff ff    	mov    -0x4(%ebx),%eax
 8048831:	85 c0                	test   %eax,%eax
 8048833:	74 05                	je     804883a <_init+0x1e>
 8048835:	e8 76 02 00 00       	call   8048ab0 <__gmon_start__@plt>
 804883a:	83 c4 08             	add    $0x8,%esp
 804883d:	5b                   	pop    %ebx
 804883e:	c3                   	ret    

Disassembly of section .plt:

08048840 <.plt>:
 8048840:	ff 35 04 d0 04 08    	pushl  0x804d004
 8048846:	ff 25 08 d0 04 08    	jmp    *0x804d008
 804884c:	00 00                	add    %al,(%eax)
	...

08048850 <strcmp@plt>:
 8048850:	ff 25 0c d0 04 08    	jmp    *0x804d00c
 8048856:	68 00 00 00 00       	push   $0x0
 804885b:	e9 e0 ff ff ff       	jmp    8048840 <.plt>

08048860 <read@plt>:
 8048860:	ff 25 10 d0 04 08    	jmp    *0x804d010
 8048866:	68 08 00 00 00       	push   $0x8
 804886b:	e9 d0 ff ff ff       	jmp    8048840 <.plt>

08048870 <srandom@plt>:
 8048870:	ff 25 14 d0 04 08    	jmp    *0x804d014
 8048876:	68 10 00 00 00       	push   $0x10
 804887b:	e9 c0 ff ff ff       	jmp    8048840 <.plt>

08048880 <printf@plt>:
 8048880:	ff 25 18 d0 04 08    	jmp    *0x804d018
 8048886:	68 18 00 00 00       	push   $0x18
 804888b:	e9 b0 ff ff ff       	jmp    8048840 <.plt>

08048890 <strdup@plt>:
 8048890:	ff 25 1c d0 04 08    	jmp    *0x804d01c
 8048896:	68 20 00 00 00       	push   $0x20
 804889b:	e9 a0 ff ff ff       	jmp    8048840 <.plt>

080488a0 <memcpy@plt>:
 80488a0:	ff 25 20 d0 04 08    	jmp    *0x804d020
 80488a6:	68 28 00 00 00       	push   $0x28
 80488ab:	e9 90 ff ff ff       	jmp    8048840 <.plt>

080488b0 <bzero@plt>:
 80488b0:	ff 25 24 d0 04 08    	jmp    *0x804d024
 80488b6:	68 30 00 00 00       	push   $0x30
 80488bb:	e9 80 ff ff ff       	jmp    8048840 <.plt>

080488c0 <signal@plt>:
 80488c0:	ff 25 28 d0 04 08    	jmp    *0x804d028
 80488c6:	68 38 00 00 00       	push   $0x38
 80488cb:	e9 70 ff ff ff       	jmp    8048840 <.plt>

080488d0 <alarm@plt>:
 80488d0:	ff 25 2c d0 04 08    	jmp    *0x804d02c
 80488d6:	68 40 00 00 00       	push   $0x40
 80488db:	e9 60 ff ff ff       	jmp    8048840 <.plt>

080488e0 <__stack_chk_fail@plt>:
 80488e0:	ff 25 30 d0 04 08    	jmp    *0x804d030
 80488e6:	68 48 00 00 00       	push   $0x48
 80488eb:	e9 50 ff ff ff       	jmp    8048840 <.plt>

080488f0 <_IO_getc@plt>:
 80488f0:	ff 25 34 d0 04 08    	jmp    *0x804d034
 80488f6:	68 50 00 00 00       	push   $0x50
 80488fb:	e9 40 ff ff ff       	jmp    8048840 <.plt>

08048900 <htons@plt>:
 8048900:	ff 25 38 d0 04 08    	jmp    *0x804d038
 8048906:	68 58 00 00 00       	push   $0x58
 804890b:	e9 30 ff ff ff       	jmp    8048840 <.plt>

08048910 <fwrite@plt>:
 8048910:	ff 25 3c d0 04 08    	jmp    *0x804d03c
 8048916:	68 60 00 00 00       	push   $0x60
 804891b:	e9 20 ff ff ff       	jmp    8048840 <.plt>

08048920 <bcopy@plt>:
 8048920:	ff 25 40 d0 04 08    	jmp    *0x804d040
 8048926:	68 68 00 00 00       	push   $0x68
 804892b:	e9 10 ff ff ff       	jmp    8048840 <.plt>

08048930 <strcpy@plt>:
 8048930:	ff 25 44 d0 04 08    	jmp    *0x804d044
 8048936:	68 70 00 00 00       	push   $0x70
 804893b:	e9 00 ff ff ff       	jmp    8048840 <.plt>

08048940 <getpid@plt>:
 8048940:	ff 25 48 d0 04 08    	jmp    *0x804d048
 8048946:	68 78 00 00 00       	push   $0x78
 804894b:	e9 f0 fe ff ff       	jmp    8048840 <.plt>

08048950 <gethostname@plt>:
 8048950:	ff 25 4c d0 04 08    	jmp    *0x804d04c
 8048956:	68 80 00 00 00       	push   $0x80
 804895b:	e9 e0 fe ff ff       	jmp    8048840 <.plt>

08048960 <puts@plt>:
 8048960:	ff 25 50 d0 04 08    	jmp    *0x804d050
 8048966:	68 88 00 00 00       	push   $0x88
 804896b:	e9 d0 fe ff ff       	jmp    8048840 <.plt>

08048970 <exit@plt>:
 8048970:	ff 25 54 d0 04 08    	jmp    *0x804d054
 8048976:	68 90 00 00 00       	push   $0x90
 804897b:	e9 c0 fe ff ff       	jmp    8048840 <.plt>

08048980 <srand@plt>:
 8048980:	ff 25 58 d0 04 08    	jmp    *0x804d058
 8048986:	68 98 00 00 00       	push   $0x98
 804898b:	e9 b0 fe ff ff       	jmp    8048840 <.plt>

08048990 <mmap@plt>:
 8048990:	ff 25 5c d0 04 08    	jmp    *0x804d05c
 8048996:	68 a0 00 00 00       	push   $0xa0
 804899b:	e9 a0 fe ff ff       	jmp    8048840 <.plt>

080489a0 <strlen@plt>:
 80489a0:	ff 25 60 d0 04 08    	jmp    *0x804d060
 80489a6:	68 a8 00 00 00       	push   $0xa8
 80489ab:	e9 90 fe ff ff       	jmp    8048840 <.plt>

080489b0 <__libc_start_main@plt>:
 80489b0:	ff 25 64 d0 04 08    	jmp    *0x804d064
 80489b6:	68 b0 00 00 00       	push   $0xb0
 80489bb:	e9 80 fe ff ff       	jmp    8048840 <.plt>

080489c0 <write@plt>:
 80489c0:	ff 25 68 d0 04 08    	jmp    *0x804d068
 80489c6:	68 b8 00 00 00       	push   $0xb8
 80489cb:	e9 70 fe ff ff       	jmp    8048840 <.plt>

080489d0 <getopt@plt>:
 80489d0:	ff 25 6c d0 04 08    	jmp    *0x804d06c
 80489d6:	68 c0 00 00 00       	push   $0xc0
 80489db:	e9 60 fe ff ff       	jmp    8048840 <.plt>

080489e0 <strcasecmp@plt>:
 80489e0:	ff 25 70 d0 04 08    	jmp    *0x804d070
 80489e6:	68 c8 00 00 00       	push   $0xc8
 80489eb:	e9 50 fe ff ff       	jmp    8048840 <.plt>

080489f0 <__isoc99_sscanf@plt>:
 80489f0:	ff 25 74 d0 04 08    	jmp    *0x804d074
 80489f6:	68 d0 00 00 00       	push   $0xd0
 80489fb:	e9 40 fe ff ff       	jmp    8048840 <.plt>

08048a00 <memset@plt>:
 8048a00:	ff 25 78 d0 04 08    	jmp    *0x804d078
 8048a06:	68 d8 00 00 00       	push   $0xd8
 8048a0b:	e9 30 fe ff ff       	jmp    8048840 <.plt>

08048a10 <__errno_location@plt>:
 8048a10:	ff 25 7c d0 04 08    	jmp    *0x804d07c
 8048a16:	68 e0 00 00 00       	push   $0xe0
 8048a1b:	e9 20 fe ff ff       	jmp    8048840 <.plt>

08048a20 <rand@plt>:
 8048a20:	ff 25 80 d0 04 08    	jmp    *0x804d080
 8048a26:	68 e8 00 00 00       	push   $0xe8
 8048a2b:	e9 10 fe ff ff       	jmp    8048840 <.plt>

08048a30 <munmap@plt>:
 8048a30:	ff 25 84 d0 04 08    	jmp    *0x804d084
 8048a36:	68 f0 00 00 00       	push   $0xf0
 8048a3b:	e9 00 fe ff ff       	jmp    8048840 <.plt>

08048a40 <sprintf@plt>:
 8048a40:	ff 25 88 d0 04 08    	jmp    *0x804d088
 8048a46:	68 f8 00 00 00       	push   $0xf8
 8048a4b:	e9 f0 fd ff ff       	jmp    8048840 <.plt>

08048a50 <socket@plt>:
 8048a50:	ff 25 8c d0 04 08    	jmp    *0x804d08c
 8048a56:	68 00 01 00 00       	push   $0x100
 8048a5b:	e9 e0 fd ff ff       	jmp    8048840 <.plt>

08048a60 <random@plt>:
 8048a60:	ff 25 90 d0 04 08    	jmp    *0x804d090
 8048a66:	68 08 01 00 00       	push   $0x108
 8048a6b:	e9 d0 fd ff ff       	jmp    8048840 <.plt>

08048a70 <gethostbyname@plt>:
 8048a70:	ff 25 94 d0 04 08    	jmp    *0x804d094
 8048a76:	68 10 01 00 00       	push   $0x110
 8048a7b:	e9 c0 fd ff ff       	jmp    8048840 <.plt>

08048a80 <connect@plt>:
 8048a80:	ff 25 98 d0 04 08    	jmp    *0x804d098
 8048a86:	68 18 01 00 00       	push   $0x118
 8048a8b:	e9 b0 fd ff ff       	jmp    8048840 <.plt>

08048a90 <close@plt>:
 8048a90:	ff 25 9c d0 04 08    	jmp    *0x804d09c
 8048a96:	68 20 01 00 00       	push   $0x120
 8048a9b:	e9 a0 fd ff ff       	jmp    8048840 <.plt>

08048aa0 <calloc@plt>:
 8048aa0:	ff 25 a0 d0 04 08    	jmp    *0x804d0a0
 8048aa6:	68 28 01 00 00       	push   $0x128
 8048aab:	e9 90 fd ff ff       	jmp    8048840 <.plt>

Disassembly of section .plt.got:

08048ab0 <__gmon_start__@plt>:
 8048ab0:	ff 25 fc cf 04 08    	jmp    *0x804cffc
 8048ab6:	66 90                	xchg   %ax,%ax

Disassembly of section .text:

08048ac0 <_start>:
 8048ac0:	31 ed                	xor    %ebp,%ebp
 8048ac2:	5e                   	pop    %esi
 8048ac3:	89 e1                	mov    %esp,%ecx
 8048ac5:	83 e4 f0             	and    $0xfffffff0,%esp
 8048ac8:	50                   	push   %eax
 8048ac9:	54                   	push   %esp
 8048aca:	52                   	push   %edx
 8048acb:	68 a0 a4 04 08       	push   $0x804a4a0
 8048ad0:	68 40 a4 04 08       	push   $0x804a440
 8048ad5:	51                   	push   %ecx
 8048ad6:	56                   	push   %esi
 8048ad7:	68 21 91 04 08       	push   $0x8049121
 8048adc:	e8 cf fe ff ff       	call   80489b0 <__libc_start_main@plt>
 8048ae1:	f4                   	hlt    
 8048ae2:	66 90                	xchg   %ax,%ax
 8048ae4:	66 90                	xchg   %ax,%ax
 8048ae6:	66 90                	xchg   %ax,%ax
 8048ae8:	66 90                	xchg   %ax,%ax
 8048aea:	66 90                	xchg   %ax,%ax
 8048aec:	66 90                	xchg   %ax,%ax
 8048aee:	66 90                	xchg   %ax,%ax

08048af0 <__x86.get_pc_thunk.bx>:
 8048af0:	8b 1c 24             	mov    (%esp),%ebx
 8048af3:	c3                   	ret    
 8048af4:	66 90                	xchg   %ax,%ax
 8048af6:	66 90                	xchg   %ax,%ax
 8048af8:	66 90                	xchg   %ax,%ax
 8048afa:	66 90                	xchg   %ax,%ax
 8048afc:	66 90                	xchg   %ax,%ax
 8048afe:	66 90                	xchg   %ax,%ax

08048b00 <deregister_tm_clones>:
 8048b00:	b8 f7 e0 04 08       	mov    $0x804e0f7,%eax
 8048b05:	2d f4 e0 04 08       	sub    $0x804e0f4,%eax
 8048b0a:	83 f8 06             	cmp    $0x6,%eax
 8048b0d:	76 1a                	jbe    8048b29 <deregister_tm_clones+0x29>
 8048b0f:	b8 00 00 00 00       	mov    $0x0,%eax
 8048b14:	85 c0                	test   %eax,%eax
 8048b16:	74 11                	je     8048b29 <deregister_tm_clones+0x29>
 8048b18:	55                   	push   %ebp
 8048b19:	89 e5                	mov    %esp,%ebp
 8048b1b:	83 ec 14             	sub    $0x14,%esp
 8048b1e:	68 f4 e0 04 08       	push   $0x804e0f4
 8048b23:	ff d0                	call   *%eax
 8048b25:	83 c4 10             	add    $0x10,%esp
 8048b28:	c9                   	leave  
 8048b29:	f3 c3                	repz ret 
 8048b2b:	90                   	nop
 8048b2c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi

08048b30 <register_tm_clones>:
 8048b30:	b8 f4 e0 04 08       	mov    $0x804e0f4,%eax
 8048b35:	2d f4 e0 04 08       	sub    $0x804e0f4,%eax
 8048b3a:	c1 f8 02             	sar    $0x2,%eax
 8048b3d:	89 c2                	mov    %eax,%edx
 8048b3f:	c1 ea 1f             	shr    $0x1f,%edx
 8048b42:	01 d0                	add    %edx,%eax
 8048b44:	d1 f8                	sar    %eax
 8048b46:	74 1b                	je     8048b63 <register_tm_clones+0x33>
 8048b48:	ba 00 00 00 00       	mov    $0x0,%edx
 8048b4d:	85 d2                	test   %edx,%edx
 8048b4f:	74 12                	je     8048b63 <register_tm_clones+0x33>
 8048b51:	55                   	push   %ebp
 8048b52:	89 e5                	mov    %esp,%ebp
 8048b54:	83 ec 10             	sub    $0x10,%esp
 8048b57:	50                   	push   %eax
 8048b58:	68 f4 e0 04 08       	push   $0x804e0f4
 8048b5d:	ff d2                	call   *%edx
 8048b5f:	83 c4 10             	add    $0x10,%esp
 8048b62:	c9                   	leave  
 8048b63:	f3 c3                	repz ret 
 8048b65:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 8048b69:	8d bc 27 00 00 00 00 	lea    0x0(%edi,%eiz,1),%edi

08048b70 <__do_global_dtors_aux>:
 8048b70:	80 3d 44 e1 04 08 00 	cmpb   $0x0,0x804e144
 8048b77:	75 13                	jne    8048b8c <__do_global_dtors_aux+0x1c>
 8048b79:	55                   	push   %ebp
 8048b7a:	89 e5                	mov    %esp,%ebp
 8048b7c:	83 ec 08             	sub    $0x8,%esp
 8048b7f:	e8 7c ff ff ff       	call   8048b00 <deregister_tm_clones>
 8048b84:	c6 05 44 e1 04 08 01 	movb   $0x1,0x804e144
 8048b8b:	c9                   	leave  
 8048b8c:	f3 c3                	repz ret 
 8048b8e:	66 90                	xchg   %ax,%ax

08048b90 <frame_dummy>:
 8048b90:	b8 10 cf 04 08       	mov    $0x804cf10,%eax
 8048b95:	8b 10                	mov    (%eax),%edx
 8048b97:	85 d2                	test   %edx,%edx
 8048b99:	75 05                	jne    8048ba0 <frame_dummy+0x10>
 8048b9b:	eb 93                	jmp    8048b30 <register_tm_clones>
 8048b9d:	8d 76 00             	lea    0x0(%esi),%esi
 8048ba0:	ba 00 00 00 00       	mov    $0x0,%edx
 8048ba5:	85 d2                	test   %edx,%edx
 8048ba7:	74 f2                	je     8048b9b <frame_dummy+0xb>
 8048ba9:	55                   	push   %ebp
 8048baa:	89 e5                	mov    %esp,%ebp
 8048bac:	83 ec 14             	sub    $0x14,%esp
 8048baf:	50                   	push   %eax
 8048bb0:	ff d2                	call   *%edx
 8048bb2:	83 c4 10             	add    $0x10,%esp
 8048bb5:	c9                   	leave  
 8048bb6:	e9 75 ff ff ff       	jmp    8048b30 <register_tm_clones>

08048bbb <smoke>:
 8048bbb:	55                   	push   %ebp
 8048bbc:	89 e5                	mov    %esp,%ebp
 8048bbe:	83 ec 08             	sub    $0x8,%esp
 8048bc1:	83 ec 0c             	sub    $0xc,%esp
 8048bc4:	68 c0 a4 04 08       	push   $0x804a4c0
 8048bc9:	e8 92 fd ff ff       	call   8048960 <puts@plt>
 8048bce:	83 c4 10             	add    $0x10,%esp
 8048bd1:	83 ec 0c             	sub    $0xc,%esp
 8048bd4:	6a 00                	push   $0x0
 8048bd6:	e8 f0 08 00 00       	call   80494cb <validate>
 8048bdb:	83 c4 10             	add    $0x10,%esp
 8048bde:	83 ec 0c             	sub    $0xc,%esp
 8048be1:	6a 00                	push   $0x0
 8048be3:	e8 88 fd ff ff       	call   8048970 <exit@plt>

08048be8 <fizz>:
 8048be8:	55                   	push   %ebp
 8048be9:	89 e5                	mov    %esp,%ebp
 8048beb:	83 ec 08             	sub    $0x8,%esp
 8048bee:	8b 55 08             	mov    0x8(%ebp),%edx
 8048bf1:	a1 58 e1 04 08       	mov    0x804e158,%eax
 8048bf6:	39 c2                	cmp    %eax,%edx
 8048bf8:	75 22                	jne    8048c1c <fizz+0x34>
 8048bfa:	83 ec 08             	sub    $0x8,%esp
 8048bfd:	ff 75 08             	pushl  0x8(%ebp)
 8048c00:	68 db a4 04 08       	push   $0x804a4db
 8048c05:	e8 76 fc ff ff       	call   8048880 <printf@plt>
 8048c0a:	83 c4 10             	add    $0x10,%esp
 8048c0d:	83 ec 0c             	sub    $0xc,%esp
 8048c10:	6a 01                	push   $0x1
 8048c12:	e8 b4 08 00 00       	call   80494cb <validate>
 8048c17:	83 c4 10             	add    $0x10,%esp
 8048c1a:	eb 13                	jmp    8048c2f <fizz+0x47>
 8048c1c:	83 ec 08             	sub    $0x8,%esp
 8048c1f:	ff 75 08             	pushl  0x8(%ebp)
 8048c22:	68 fc a4 04 08       	push   $0x804a4fc
 8048c27:	e8 54 fc ff ff       	call   8048880 <printf@plt>
 8048c2c:	83 c4 10             	add    $0x10,%esp
 8048c2f:	83 ec 0c             	sub    $0xc,%esp
 8048c32:	6a 00                	push   $0x0
 8048c34:	e8 37 fd ff ff       	call   8048970 <exit@plt>

08048c39 <bang>:
 8048c39:	55                   	push   %ebp
 8048c3a:	89 e5                	mov    %esp,%ebp
 8048c3c:	83 ec 08             	sub    $0x8,%esp
 8048c3f:	a1 60 e1 04 08       	mov    0x804e160,%eax
 8048c44:	89 c2                	mov    %eax,%edx
 8048c46:	a1 58 e1 04 08       	mov    0x804e158,%eax
 8048c4b:	39 c2                	cmp    %eax,%edx
 8048c4d:	75 25                	jne    8048c74 <bang+0x3b>
 8048c4f:	a1 60 e1 04 08       	mov    0x804e160,%eax
 8048c54:	83 ec 08             	sub    $0x8,%esp
 8048c57:	50                   	push   %eax
 8048c58:	68 1c a5 04 08       	push   $0x804a51c
 8048c5d:	e8 1e fc ff ff       	call   8048880 <printf@plt>
 8048c62:	83 c4 10             	add    $0x10,%esp
 8048c65:	83 ec 0c             	sub    $0xc,%esp
 8048c68:	6a 02                	push   $0x2
 8048c6a:	e8 5c 08 00 00       	call   80494cb <validate>
 8048c6f:	83 c4 10             	add    $0x10,%esp
 8048c72:	eb 16                	jmp    8048c8a <bang+0x51>
 8048c74:	a1 60 e1 04 08       	mov    0x804e160,%eax
 8048c79:	83 ec 08             	sub    $0x8,%esp
 8048c7c:	50                   	push   %eax
 8048c7d:	68 41 a5 04 08       	push   $0x804a541
 8048c82:	e8 f9 fb ff ff       	call   8048880 <printf@plt>
 8048c87:	83 c4 10             	add    $0x10,%esp
 8048c8a:	83 ec 0c             	sub    $0xc,%esp
 8048c8d:	6a 00                	push   $0x0
 8048c8f:	e8 dc fc ff ff       	call   8048970 <exit@plt>

08048c94 <test>:
 8048c94:	55                   	push   %ebp
 8048c95:	89 e5                	mov    %esp,%ebp
 8048c97:	83 ec 18             	sub    $0x18,%esp
 8048c9a:	e8 64 04 00 00       	call   8049103 <uniqueval>
 8048c9f:	89 45 f0             	mov    %eax,-0x10(%ebp)
 8048ca2:	e8 d1 06 00 00       	call   8049378 <getbuf>
 8048ca7:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8048caa:	e8 54 04 00 00       	call   8049103 <uniqueval>
 8048caf:	89 c2                	mov    %eax,%edx
 8048cb1:	8b 45 f0             	mov    -0x10(%ebp),%eax
 8048cb4:	39 c2                	cmp    %eax,%edx
 8048cb6:	74 12                	je     8048cca <test+0x36>
 8048cb8:	83 ec 0c             	sub    $0xc,%esp
 8048cbb:	68 60 a5 04 08       	push   $0x804a560
 8048cc0:	e8 9b fc ff ff       	call   8048960 <puts@plt>
 8048cc5:	83 c4 10             	add    $0x10,%esp
 8048cc8:	eb 41                	jmp    8048d0b <test+0x77>
 8048cca:	8b 55 f4             	mov    -0xc(%ebp),%edx
 8048ccd:	a1 58 e1 04 08       	mov    0x804e158,%eax
 8048cd2:	39 c2                	cmp    %eax,%edx
 8048cd4:	75 22                	jne    8048cf8 <test+0x64>
 8048cd6:	83 ec 08             	sub    $0x8,%esp
 8048cd9:	ff 75 f4             	pushl  -0xc(%ebp)
 8048cdc:	68 89 a5 04 08       	push   $0x804a589
 8048ce1:	e8 9a fb ff ff       	call   8048880 <printf@plt>
 8048ce6:	83 c4 10             	add    $0x10,%esp
 8048ce9:	83 ec 0c             	sub    $0xc,%esp
 8048cec:	6a 03                	push   $0x3
 8048cee:	e8 d8 07 00 00       	call   80494cb <validate>
 8048cf3:	83 c4 10             	add    $0x10,%esp
 8048cf6:	eb 13                	jmp    8048d0b <test+0x77>
 8048cf8:	83 ec 08             	sub    $0x8,%esp
 8048cfb:	ff 75 f4             	pushl  -0xc(%ebp)
 8048cfe:	68 a6 a5 04 08       	push   $0x804a5a6
 8048d03:	e8 78 fb ff ff       	call   8048880 <printf@plt>
 8048d08:	83 c4 10             	add    $0x10,%esp
 8048d0b:	90                   	nop
 8048d0c:	c9                   	leave  
 8048d0d:	c3                   	ret    

08048d0e <testn>:
 8048d0e:	55                   	push   %ebp
 8048d0f:	89 e5                	mov    %esp,%ebp
 8048d11:	83 ec 18             	sub    $0x18,%esp
 8048d14:	e8 ea 03 00 00       	call   8049103 <uniqueval>
 8048d19:	89 45 f0             	mov    %eax,-0x10(%ebp)
 8048d1c:	e8 73 06 00 00       	call   8049394 <getbufn>
 8048d21:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8048d24:	e8 da 03 00 00       	call   8049103 <uniqueval>
 8048d29:	89 c2                	mov    %eax,%edx
 8048d2b:	8b 45 f0             	mov    -0x10(%ebp),%eax
 8048d2e:	39 c2                	cmp    %eax,%edx
 8048d30:	74 12                	je     8048d44 <testn+0x36>
 8048d32:	83 ec 0c             	sub    $0xc,%esp
 8048d35:	68 60 a5 04 08       	push   $0x804a560
 8048d3a:	e8 21 fc ff ff       	call   8048960 <puts@plt>
 8048d3f:	83 c4 10             	add    $0x10,%esp
 8048d42:	eb 41                	jmp    8048d85 <testn+0x77>
 8048d44:	8b 55 f4             	mov    -0xc(%ebp),%edx
 8048d47:	a1 58 e1 04 08       	mov    0x804e158,%eax
 8048d4c:	39 c2                	cmp    %eax,%edx
 8048d4e:	75 22                	jne    8048d72 <testn+0x64>
 8048d50:	83 ec 08             	sub    $0x8,%esp
 8048d53:	ff 75 f4             	pushl  -0xc(%ebp)
 8048d56:	68 c4 a5 04 08       	push   $0x804a5c4
 8048d5b:	e8 20 fb ff ff       	call   8048880 <printf@plt>
 8048d60:	83 c4 10             	add    $0x10,%esp
 8048d63:	83 ec 0c             	sub    $0xc,%esp
 8048d66:	6a 04                	push   $0x4
 8048d68:	e8 5e 07 00 00       	call   80494cb <validate>
 8048d6d:	83 c4 10             	add    $0x10,%esp
 8048d70:	eb 13                	jmp    8048d85 <testn+0x77>
 8048d72:	83 ec 08             	sub    $0x8,%esp
 8048d75:	ff 75 f4             	pushl  -0xc(%ebp)
 8048d78:	68 e4 a5 04 08       	push   $0x804a5e4
 8048d7d:	e8 fe fa ff ff       	call   8048880 <printf@plt>
 8048d82:	83 c4 10             	add    $0x10,%esp
 8048d85:	90                   	nop
 8048d86:	c9                   	leave  
 8048d87:	c3                   	ret    

08048d88 <save_char>:
 8048d88:	55                   	push   %ebp
 8048d89:	89 e5                	mov    %esp,%ebp
 8048d8b:	83 ec 04             	sub    $0x4,%esp
 8048d8e:	8b 45 08             	mov    0x8(%ebp),%eax
 8048d91:	88 45 fc             	mov    %al,-0x4(%ebp)
 8048d94:	a1 64 e1 04 08       	mov    0x804e164,%eax
 8048d99:	3d ff 03 00 00       	cmp    $0x3ff,%eax
 8048d9e:	7f 6c                	jg     8048e0c <save_char+0x84>
 8048da0:	8b 15 64 e1 04 08    	mov    0x804e164,%edx
 8048da6:	89 d0                	mov    %edx,%eax
 8048da8:	01 c0                	add    %eax,%eax
 8048daa:	01 c2                	add    %eax,%edx
 8048dac:	0f b6 45 fc          	movzbl -0x4(%ebp),%eax
 8048db0:	c0 f8 04             	sar    $0x4,%al
 8048db3:	0f be c0             	movsbl %al,%eax
 8048db6:	83 e0 0f             	and    $0xf,%eax
 8048db9:	0f b6 80 c8 d0 04 08 	movzbl 0x804d0c8(%eax),%eax
 8048dc0:	88 82 a0 e1 04 08    	mov    %al,0x804e1a0(%edx)
 8048dc6:	8b 15 64 e1 04 08    	mov    0x804e164,%edx
 8048dcc:	89 d0                	mov    %edx,%eax
 8048dce:	01 c0                	add    %eax,%eax
 8048dd0:	01 d0                	add    %edx,%eax
 8048dd2:	8d 50 01             	lea    0x1(%eax),%edx
 8048dd5:	0f be 45 fc          	movsbl -0x4(%ebp),%eax
 8048dd9:	83 e0 0f             	and    $0xf,%eax
 8048ddc:	0f b6 80 c8 d0 04 08 	movzbl 0x804d0c8(%eax),%eax
 8048de3:	88 82 a0 e1 04 08    	mov    %al,0x804e1a0(%edx)
 8048de9:	8b 15 64 e1 04 08    	mov    0x804e164,%edx
 8048def:	89 d0                	mov    %edx,%eax
 8048df1:	01 c0                	add    %eax,%eax
 8048df3:	01 d0                	add    %edx,%eax
 8048df5:	83 c0 02             	add    $0x2,%eax
 8048df8:	c6 80 a0 e1 04 08 20 	movb   $0x20,0x804e1a0(%eax)
 8048dff:	a1 64 e1 04 08       	mov    0x804e164,%eax
 8048e04:	83 c0 01             	add    $0x1,%eax
 8048e07:	a3 64 e1 04 08       	mov    %eax,0x804e164
 8048e0c:	90                   	nop
 8048e0d:	c9                   	leave  
 8048e0e:	c3                   	ret    

08048e0f <save_term>:
 8048e0f:	55                   	push   %ebp
 8048e10:	89 e5                	mov    %esp,%ebp
 8048e12:	8b 15 64 e1 04 08    	mov    0x804e164,%edx
 8048e18:	89 d0                	mov    %edx,%eax
 8048e1a:	01 c0                	add    %eax,%eax
 8048e1c:	01 d0                	add    %edx,%eax
 8048e1e:	c6 80 a0 e1 04 08 00 	movb   $0x0,0x804e1a0(%eax)
 8048e25:	90                   	nop
 8048e26:	5d                   	pop    %ebp
 8048e27:	c3                   	ret    

08048e28 <Gets>:
 8048e28:	55                   	push   %ebp
 8048e29:	89 e5                	mov    %esp,%ebp
 8048e2b:	83 ec 18             	sub    $0x18,%esp
 8048e2e:	8b 45 08             	mov    0x8(%ebp),%eax
 8048e31:	89 45 f0             	mov    %eax,-0x10(%ebp)
 8048e34:	c7 05 64 e1 04 08 00 	movl   $0x0,0x804e164
 8048e3b:	00 00 00 
 8048e3e:	eb 1d                	jmp    8048e5d <Gets+0x35>
 8048e40:	8b 45 f0             	mov    -0x10(%ebp),%eax
 8048e43:	8d 50 01             	lea    0x1(%eax),%edx
 8048e46:	89 55 f0             	mov    %edx,-0x10(%ebp)
 8048e49:	8b 55 f4             	mov    -0xc(%ebp),%edx
 8048e4c:	88 10                	mov    %dl,(%eax)
 8048e4e:	8b 45 f4             	mov    -0xc(%ebp),%eax
 8048e51:	0f be c0             	movsbl %al,%eax
 8048e54:	50                   	push   %eax
 8048e55:	e8 2e ff ff ff       	call   8048d88 <save_char>
 8048e5a:	83 c4 04             	add    $0x4,%esp
 8048e5d:	a1 54 e1 04 08       	mov    0x804e154,%eax
 8048e62:	83 ec 0c             	sub    $0xc,%esp
 8048e65:	50                   	push   %eax
 8048e66:	e8 85 fa ff ff       	call   80488f0 <_IO_getc@plt>
 8048e6b:	83 c4 10             	add    $0x10,%esp
 8048e6e:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8048e71:	83 7d f4 ff          	cmpl   $0xffffffff,-0xc(%ebp)
 8048e75:	74 06                	je     8048e7d <Gets+0x55>
 8048e77:	83 7d f4 0a          	cmpl   $0xa,-0xc(%ebp)
 8048e7b:	75 c3                	jne    8048e40 <Gets+0x18>
 8048e7d:	8b 45 f0             	mov    -0x10(%ebp),%eax
 8048e80:	8d 50 01             	lea    0x1(%eax),%edx
 8048e83:	89 55 f0             	mov    %edx,-0x10(%ebp)
 8048e86:	c6 00 00             	movb   $0x0,(%eax)
 8048e89:	e8 81 ff ff ff       	call   8048e0f <save_term>
 8048e8e:	8b 45 08             	mov    0x8(%ebp),%eax
 8048e91:	c9                   	leave  
 8048e92:	c3                   	ret    

08048e93 <usage>:
 8048e93:	55                   	push   %ebp
 8048e94:	89 e5                	mov    %esp,%ebp
 8048e96:	83 ec 08             	sub    $0x8,%esp
 8048e99:	83 ec 08             	sub    $0x8,%esp
 8048e9c:	ff 75 08             	pushl  0x8(%ebp)
 8048e9f:	68 00 a6 04 08       	push   $0x804a600
 8048ea4:	e8 d7 f9 ff ff       	call   8048880 <printf@plt>
 8048ea9:	83 c4 10             	add    $0x10,%esp
 8048eac:	83 ec 0c             	sub    $0xc,%esp
 8048eaf:	68 1e a6 04 08       	push   $0x804a61e
 8048eb4:	e8 a7 fa ff ff       	call   8048960 <puts@plt>
 8048eb9:	83 c4 10             	add    $0x10,%esp
 8048ebc:	83 ec 0c             	sub    $0xc,%esp
 8048ebf:	68 34 a6 04 08       	push   $0x804a634
 8048ec4:	e8 97 fa ff ff       	call   8048960 <puts@plt>
 8048ec9:	83 c4 10             	add    $0x10,%esp
 8048ecc:	83 ec 0c             	sub    $0xc,%esp
 8048ecf:	68 50 a6 04 08       	push   $0x804a650
 8048ed4:	e8 87 fa ff ff       	call   8048960 <puts@plt>
 8048ed9:	83 c4 10             	add    $0x10,%esp
 8048edc:	83 ec 0c             	sub    $0xc,%esp
 8048edf:	68 8c a6 04 08       	push   $0x804a68c
 8048ee4:	e8 77 fa ff ff       	call   8048960 <puts@plt>
 8048ee9:	83 c4 10             	add    $0x10,%esp
 8048eec:	83 ec 0c             	sub    $0xc,%esp
 8048eef:	6a 00                	push   $0x0
 8048ef1:	e8 7a fa ff ff       	call   8048970 <exit@plt>

08048ef6 <bushandler>:
 8048ef6:	55                   	push   %ebp
 8048ef7:	89 e5                	mov    %esp,%ebp
 8048ef9:	83 ec 08             	sub    $0x8,%esp
 8048efc:	83 ec 0c             	sub    $0xc,%esp
 8048eff:	68 b4 a6 04 08       	push   $0x804a6b4
 8048f04:	e8 57 fa ff ff       	call   8048960 <puts@plt>
 8048f09:	83 c4 10             	add    $0x10,%esp
 8048f0c:	83 ec 0c             	sub    $0xc,%esp
 8048f0f:	68 d4 a6 04 08       	push   $0x804a6d4
 8048f14:	e8 47 fa ff ff       	call   8048960 <puts@plt>
 8048f19:	83 c4 10             	add    $0x10,%esp
 8048f1c:	83 ec 0c             	sub    $0xc,%esp
 8048f1f:	6a 00                	push   $0x0
 8048f21:	e8 4a fa ff ff       	call   8048970 <exit@plt>

08048f26 <seghandler>:
 8048f26:	55                   	push   %ebp
 8048f27:	89 e5                	mov    %esp,%ebp
 8048f29:	83 ec 08             	sub    $0x8,%esp
 8048f2c:	83 ec 0c             	sub    $0xc,%esp
 8048f2f:	68 ec a6 04 08       	push   $0x804a6ec
 8048f34:	e8 27 fa ff ff       	call   8048960 <puts@plt>
 8048f39:	83 c4 10             	add    $0x10,%esp
 8048f3c:	83 ec 0c             	sub    $0xc,%esp
 8048f3f:	68 d4 a6 04 08       	push   $0x804a6d4
 8048f44:	e8 17 fa ff ff       	call   8048960 <puts@plt>
 8048f49:	83 c4 10             	add    $0x10,%esp
 8048f4c:	83 ec 0c             	sub    $0xc,%esp
 8048f4f:	6a 00                	push   $0x0
 8048f51:	e8 1a fa ff ff       	call   8048970 <exit@plt>

08048f56 <illegalhandler>:
 8048f56:	55                   	push   %ebp
 8048f57:	89 e5                	mov    %esp,%ebp
 8048f59:	83 ec 08             	sub    $0x8,%esp
 8048f5c:	83 ec 0c             	sub    $0xc,%esp
 8048f5f:	68 14 a7 04 08       	push   $0x804a714
 8048f64:	e8 f7 f9 ff ff       	call   8048960 <puts@plt>
 8048f69:	83 c4 10             	add    $0x10,%esp
 8048f6c:	83 ec 0c             	sub    $0xc,%esp
 8048f6f:	68 d4 a6 04 08       	push   $0x804a6d4
 8048f74:	e8 e7 f9 ff ff       	call   8048960 <puts@plt>
 8048f79:	83 c4 10             	add    $0x10,%esp
 8048f7c:	83 ec 0c             	sub    $0xc,%esp
 8048f7f:	6a 00                	push   $0x0
 8048f81:	e8 ea f9 ff ff       	call   8048970 <exit@plt>

08048f86 <launch>:
 8048f86:	55                   	push   %ebp
 8048f87:	89 e5                	mov    %esp,%ebp
 8048f89:	83 ec 58             	sub    $0x58,%esp
 8048f8c:	65 a1 14 00 00 00    	mov    %gs:0x14,%eax
 8048f92:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8048f95:	31 c0                	xor    %eax,%eax
 8048f97:	c7 45 ac 00 00 00 00 	movl   $0x0,-0x54(%ebp)
 8048f9e:	8d 45 b4             	lea    -0x4c(%ebp),%eax
 8048fa1:	25 f0 3f 00 00       	and    $0x3ff0,%eax
 8048fa6:	89 45 ac             	mov    %eax,-0x54(%ebp)
 8048fa9:	8b 55 0c             	mov    0xc(%ebp),%edx
 8048fac:	8b 45 ac             	mov    -0x54(%ebp),%eax
 8048faf:	01 d0                	add    %edx,%eax
 8048fb1:	8d 50 0f             	lea    0xf(%eax),%edx
 8048fb4:	b8 10 00 00 00       	mov    $0x10,%eax
 8048fb9:	83 e8 01             	sub    $0x1,%eax
 8048fbc:	01 d0                	add    %edx,%eax
 8048fbe:	b9 10 00 00 00       	mov    $0x10,%ecx
 8048fc3:	ba 00 00 00 00       	mov    $0x0,%edx
 8048fc8:	f7 f1                	div    %ecx
 8048fca:	6b c0 10             	imul   $0x10,%eax,%eax
 8048fcd:	29 c4                	sub    %eax,%esp
 8048fcf:	89 e0                	mov    %esp,%eax
 8048fd1:	83 c0 0f             	add    $0xf,%eax
 8048fd4:	c1 e8 04             	shr    $0x4,%eax
 8048fd7:	c1 e0 04             	shl    $0x4,%eax
 8048fda:	89 45 b0             	mov    %eax,-0x50(%ebp)
 8048fdd:	83 ec 04             	sub    $0x4,%esp
 8048fe0:	ff 75 ac             	pushl  -0x54(%ebp)
 8048fe3:	68 f4 00 00 00       	push   $0xf4
 8048fe8:	ff 75 b0             	pushl  -0x50(%ebp)
 8048feb:	e8 10 fa ff ff       	call   8048a00 <memset@plt>
 8048ff0:	83 c4 10             	add    $0x10,%esp
 8048ff3:	83 ec 0c             	sub    $0xc,%esp
 8048ff6:	68 3f a7 04 08       	push   $0x804a73f
 8048ffb:	e8 80 f8 ff ff       	call   8048880 <printf@plt>
 8049000:	83 c4 10             	add    $0x10,%esp
 8049003:	83 7d 08 00          	cmpl   $0x0,0x8(%ebp)
 8049007:	74 07                	je     8049010 <launch+0x8a>
 8049009:	e8 00 fd ff ff       	call   8048d0e <testn>
 804900e:	eb 05                	jmp    8049015 <launch+0x8f>
 8049010:	e8 7f fc ff ff       	call   8048c94 <test>
 8049015:	a1 5c e1 04 08       	mov    0x804e15c,%eax
 804901a:	85 c0                	test   %eax,%eax
 804901c:	75 1a                	jne    8049038 <launch+0xb2>
 804901e:	83 ec 0c             	sub    $0xc,%esp
 8049021:	68 d4 a6 04 08       	push   $0x804a6d4
 8049026:	e8 35 f9 ff ff       	call   8048960 <puts@plt>
 804902b:	83 c4 10             	add    $0x10,%esp
 804902e:	c7 05 5c e1 04 08 00 	movl   $0x0,0x804e15c
 8049035:	00 00 00 
 8049038:	90                   	nop
 8049039:	8b 45 f4             	mov    -0xc(%ebp),%eax
 804903c:	65 33 05 14 00 00 00 	xor    %gs:0x14,%eax
 8049043:	74 05                	je     804904a <launch+0xc4>
 8049045:	e8 96 f8 ff ff       	call   80488e0 <__stack_chk_fail@plt>
 804904a:	c9                   	leave  
 804904b:	c3                   	ret    

0804904c <launcher>:
 804904c:	55                   	push   %ebp
 804904d:	89 e5                	mov    %esp,%ebp
 804904f:	83 ec 18             	sub    $0x18,%esp
 8049052:	8b 45 08             	mov    0x8(%ebp),%eax
 8049055:	a3 68 e1 04 08       	mov    %eax,0x804e168
 804905a:	8b 45 0c             	mov    0xc(%ebp),%eax
 804905d:	a3 6c e1 04 08       	mov    %eax,0x804e16c
 8049062:	83 ec 08             	sub    $0x8,%esp
 8049065:	6a 00                	push   $0x0
 8049067:	6a 00                	push   $0x0
 8049069:	68 32 01 00 00       	push   $0x132
 804906e:	6a 07                	push   $0x7
 8049070:	68 00 00 10 00       	push   $0x100000
 8049075:	68 00 60 58 55       	push   $0x55586000
 804907a:	e8 11 f9 ff ff       	call   8048990 <mmap@plt>
 804907f:	83 c4 20             	add    $0x20,%esp
 8049082:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8049085:	81 7d f4 00 60 58 55 	cmpl   $0x55586000,-0xc(%ebp)
 804908c:	74 21                	je     80490af <launcher+0x63>
 804908e:	a1 00 e1 04 08       	mov    0x804e100,%eax
 8049093:	50                   	push   %eax
 8049094:	6a 47                	push   $0x47
 8049096:	6a 01                	push   $0x1
 8049098:	68 4c a7 04 08       	push   $0x804a74c
 804909d:	e8 6e f8 ff ff       	call   8048910 <fwrite@plt>
 80490a2:	83 c4 10             	add    $0x10,%esp
 80490a5:	83 ec 0c             	sub    $0xc,%esp
 80490a8:	6a 01                	push   $0x1
 80490aa:	e8 c1 f8 ff ff       	call   8048970 <exit@plt>
 80490af:	8b 45 f4             	mov    -0xc(%ebp),%eax
 80490b2:	05 f8 ff 0f 00       	add    $0xffff8,%eax
 80490b7:	a3 80 e1 04 08       	mov    %eax,0x804e180
 80490bc:	8b 15 80 e1 04 08    	mov    0x804e180,%edx
 80490c2:	89 e0                	mov    %esp,%eax
 80490c4:	89 d4                	mov    %edx,%esp
 80490c6:	89 c2                	mov    %eax,%edx
 80490c8:	89 15 70 e1 04 08    	mov    %edx,0x804e170
 80490ce:	8b 15 6c e1 04 08    	mov    0x804e16c,%edx
 80490d4:	a1 68 e1 04 08       	mov    0x804e168,%eax
 80490d9:	83 ec 08             	sub    $0x8,%esp
 80490dc:	52                   	push   %edx
 80490dd:	50                   	push   %eax
 80490de:	e8 a3 fe ff ff       	call   8048f86 <launch>
 80490e3:	83 c4 10             	add    $0x10,%esp
 80490e6:	a1 70 e1 04 08       	mov    0x804e170,%eax
 80490eb:	89 c4                	mov    %eax,%esp
 80490ed:	83 ec 08             	sub    $0x8,%esp
 80490f0:	68 00 00 10 00       	push   $0x100000
 80490f5:	ff 75 f4             	pushl  -0xc(%ebp)
 80490f8:	e8 33 f9 ff ff       	call   8048a30 <munmap@plt>
 80490fd:	83 c4 10             	add    $0x10,%esp
 8049100:	90                   	nop
 8049101:	c9                   	leave  
 8049102:	c3                   	ret    

08049103 <uniqueval>:
 8049103:	55                   	push   %ebp
 8049104:	89 e5                	mov    %esp,%ebp
 8049106:	83 ec 08             	sub    $0x8,%esp
 8049109:	e8 32 f8 ff ff       	call   8048940 <getpid@plt>
 804910e:	83 ec 0c             	sub    $0xc,%esp
 8049111:	50                   	push   %eax
 8049112:	e8 59 f7 ff ff       	call   8048870 <srandom@plt>
 8049117:	83 c4 10             	add    $0x10,%esp
 804911a:	e8 41 f9 ff ff       	call   8048a60 <random@plt>
 804911f:	c9                   	leave  
 8049120:	c3                   	ret    

08049121 <main>:
 8049121:	8d 4c 24 04          	lea    0x4(%esp),%ecx
 8049125:	83 e4 f0             	and    $0xfffffff0,%esp
 8049128:	ff 71 fc             	pushl  -0x4(%ecx)
 804912b:	55                   	push   %ebp
 804912c:	89 e5                	mov    %esp,%ebp
 804912e:	53                   	push   %ebx
 804912f:	51                   	push   %ecx
 8049130:	83 ec 20             	sub    $0x20,%esp
 8049133:	89 cb                	mov    %ecx,%ebx
 8049135:	c7 45 f0 00 00 00 00 	movl   $0x0,-0x10(%ebp)
 804913c:	c7 45 e4 00 00 00 00 	movl   $0x0,-0x1c(%ebp)
 8049143:	c7 45 ec 01 00 00 00 	movl   $0x1,-0x14(%ebp)
 804914a:	83 ec 08             	sub    $0x8,%esp
 804914d:	68 26 8f 04 08       	push   $0x8048f26
 8049152:	6a 0b                	push   $0xb
 8049154:	e8 67 f7 ff ff       	call   80488c0 <signal@plt>
 8049159:	83 c4 10             	add    $0x10,%esp
 804915c:	83 ec 08             	sub    $0x8,%esp
 804915f:	68 f6 8e 04 08       	push   $0x8048ef6
 8049164:	6a 07                	push   $0x7
 8049166:	e8 55 f7 ff ff       	call   80488c0 <signal@plt>
 804916b:	83 c4 10             	add    $0x10,%esp
 804916e:	83 ec 08             	sub    $0x8,%esp
 8049171:	68 56 8f 04 08       	push   $0x8048f56
 8049176:	6a 04                	push   $0x4
 8049178:	e8 43 f7 ff ff       	call   80488c0 <signal@plt>
 804917d:	83 c4 10             	add    $0x10,%esp
 8049180:	a1 20 e1 04 08       	mov    0x804e120,%eax
 8049185:	a3 54 e1 04 08       	mov    %eax,0x804e154
 804918a:	e9 a3 00 00 00       	jmp    8049232 <main+0x111>
 804918f:	0f be 45 e3          	movsbl -0x1d(%ebp),%eax
 8049193:	83 e8 67             	sub    $0x67,%eax
 8049196:	83 f8 0e             	cmp    $0xe,%eax
 8049199:	0f 87 82 00 00 00    	ja     8049221 <main+0x100>
 804919f:	8b 04 85 0c a8 04 08 	mov    0x804a80c(,%eax,4),%eax
 80491a6:	ff e0                	jmp    *%eax
 80491a8:	8b 43 04             	mov    0x4(%ebx),%eax
 80491ab:	8b 00                	mov    (%eax),%eax
 80491ad:	83 ec 0c             	sub    $0xc,%esp
 80491b0:	50                   	push   %eax
 80491b1:	e8 dd fc ff ff       	call   8048e93 <usage>
 80491b6:	83 c4 10             	add    $0x10,%esp
 80491b9:	eb 77                	jmp    8049232 <main+0x111>
 80491bb:	c7 45 e4 01 00 00 00 	movl   $0x1,-0x1c(%ebp)
 80491c2:	c7 45 ec 05 00 00 00 	movl   $0x5,-0x14(%ebp)
 80491c9:	eb 67                	jmp    8049232 <main+0x111>
 80491cb:	a1 40 e1 04 08       	mov    0x804e140,%eax
 80491d0:	83 ec 0c             	sub    $0xc,%esp
 80491d3:	50                   	push   %eax
 80491d4:	e8 b7 f6 ff ff       	call   8048890 <strdup@plt>
 80491d9:	83 c4 10             	add    $0x10,%esp
 80491dc:	a3 48 e1 04 08       	mov    %eax,0x804e148
 80491e1:	a1 48 e1 04 08       	mov    0x804e148,%eax
 80491e6:	83 ec 0c             	sub    $0xc,%esp
 80491e9:	50                   	push   %eax
 80491ea:	e8 0b 12 00 00       	call   804a3fa <gencookie>
 80491ef:	83 c4 10             	add    $0x10,%esp
 80491f2:	a3 58 e1 04 08       	mov    %eax,0x804e158
 80491f7:	eb 39                	jmp    8049232 <main+0x111>
 80491f9:	83 ec 0c             	sub    $0xc,%esp
 80491fc:	68 94 a7 04 08       	push   $0x804a794
 8049201:	e8 5a f7 ff ff       	call   8048960 <puts@plt>
 8049206:	83 c4 10             	add    $0x10,%esp
 8049209:	c7 05 4c e1 04 08 00 	movl   $0x0,0x804e14c
 8049210:	00 00 00 
 8049213:	eb 1d                	jmp    8049232 <main+0x111>
 8049215:	c7 05 50 e1 04 08 01 	movl   $0x1,0x804e150
 804921c:	00 00 00 
 804921f:	eb 11                	jmp    8049232 <main+0x111>
 8049221:	8b 43 04             	mov    0x4(%ebx),%eax
 8049224:	8b 00                	mov    (%eax),%eax
 8049226:	83 ec 0c             	sub    $0xc,%esp
 8049229:	50                   	push   %eax
 804922a:	e8 64 fc ff ff       	call   8048e93 <usage>
 804922f:	83 c4 10             	add    $0x10,%esp
 8049232:	83 ec 04             	sub    $0x4,%esp
 8049235:	68 bc a7 04 08       	push   $0x804a7bc
 804923a:	ff 73 04             	pushl  0x4(%ebx)
 804923d:	ff 33                	pushl  (%ebx)
 804923f:	e8 8c f7 ff ff       	call   80489d0 <getopt@plt>
 8049244:	83 c4 10             	add    $0x10,%esp
 8049247:	88 45 e3             	mov    %al,-0x1d(%ebp)
 804924a:	80 7d e3 ff          	cmpb   $0xff,-0x1d(%ebp)
 804924e:	0f 85 3b ff ff ff    	jne    804918f <main+0x6e>
 8049254:	a1 48 e1 04 08       	mov    0x804e148,%eax
 8049259:	85 c0                	test   %eax,%eax
 804925b:	75 27                	jne    8049284 <main+0x163>
 804925d:	8b 43 04             	mov    0x4(%ebx),%eax
 8049260:	8b 00                	mov    (%eax),%eax
 8049262:	83 ec 08             	sub    $0x8,%esp
 8049265:	50                   	push   %eax
 8049266:	68 c4 a7 04 08       	push   $0x804a7c4
 804926b:	e8 10 f6 ff ff       	call   8048880 <printf@plt>
 8049270:	83 c4 10             	add    $0x10,%esp
 8049273:	8b 43 04             	mov    0x4(%ebx),%eax
 8049276:	8b 00                	mov    (%eax),%eax
 8049278:	83 ec 0c             	sub    $0xc,%esp
 804927b:	50                   	push   %eax
 804927c:	e8 12 fc ff ff       	call   8048e93 <usage>
 8049281:	83 c4 10             	add    $0x10,%esp
 8049284:	e8 2d 01 00 00       	call   80493b6 <initialize_bomb>
 8049289:	a1 48 e1 04 08       	mov    0x804e148,%eax
 804928e:	83 ec 08             	sub    $0x8,%esp
 8049291:	50                   	push   %eax
 8049292:	68 f0 a7 04 08       	push   $0x804a7f0
 8049297:	e8 e4 f5 ff ff       	call   8048880 <printf@plt>
 804929c:	83 c4 10             	add    $0x10,%esp
 804929f:	a1 58 e1 04 08       	mov    0x804e158,%eax
 80492a4:	83 ec 08             	sub    $0x8,%esp
 80492a7:	50                   	push   %eax
 80492a8:	68 fc a7 04 08       	push   $0x804a7fc
 80492ad:	e8 ce f5 ff ff       	call   8048880 <printf@plt>
 80492b2:	83 c4 10             	add    $0x10,%esp
 80492b5:	a1 58 e1 04 08       	mov    0x804e158,%eax
 80492ba:	83 ec 0c             	sub    $0xc,%esp
 80492bd:	50                   	push   %eax
 80492be:	e8 ad f5 ff ff       	call   8048870 <srandom@plt>
 80492c3:	83 c4 10             	add    $0x10,%esp
 80492c6:	e8 95 f7 ff ff       	call   8048a60 <random@plt>
 80492cb:	25 f0 0f 00 00       	and    $0xff0,%eax
 80492d0:	05 00 01 00 00       	add    $0x100,%eax
 80492d5:	89 45 f0             	mov    %eax,-0x10(%ebp)
 80492d8:	8b 45 ec             	mov    -0x14(%ebp),%eax
 80492db:	83 ec 08             	sub    $0x8,%esp
 80492de:	6a 04                	push   $0x4
 80492e0:	50                   	push   %eax
 80492e1:	e8 ba f7 ff ff       	call   8048aa0 <calloc@plt>
 80492e6:	83 c4 10             	add    $0x10,%esp
 80492e9:	89 45 f4             	mov    %eax,-0xc(%ebp)
 80492ec:	8b 45 f4             	mov    -0xc(%ebp),%eax
 80492ef:	c7 00 00 00 00 00    	movl   $0x0,(%eax)
 80492f5:	c7 45 e8 01 00 00 00 	movl   $0x1,-0x18(%ebp)
 80492fc:	eb 29                	jmp    8049327 <main+0x206>
 80492fe:	8b 45 e8             	mov    -0x18(%ebp),%eax
 8049301:	8d 14 85 00 00 00 00 	lea    0x0(,%eax,4),%edx
 8049308:	8b 45 f4             	mov    -0xc(%ebp),%eax
 804930b:	8d 1c 02             	lea    (%edx,%eax,1),%ebx
 804930e:	e8 4d f7 ff ff       	call   8048a60 <random@plt>
 8049313:	25 f0 00 00 00       	and    $0xf0,%eax
 8049318:	ba 80 00 00 00       	mov    $0x80,%edx
 804931d:	29 c2                	sub    %eax,%edx
 804931f:	89 d0                	mov    %edx,%eax
 8049321:	89 03                	mov    %eax,(%ebx)
 8049323:	83 45 e8 01          	addl   $0x1,-0x18(%ebp)
 8049327:	8b 45 e8             	mov    -0x18(%ebp),%eax
 804932a:	3b 45 ec             	cmp    -0x14(%ebp),%eax
 804932d:	7c cf                	jl     80492fe <main+0x1dd>
 804932f:	c7 45 e8 00 00 00 00 	movl   $0x0,-0x18(%ebp)
 8049336:	eb 29                	jmp    8049361 <main+0x240>
 8049338:	8b 45 e8             	mov    -0x18(%ebp),%eax
 804933b:	8d 14 85 00 00 00 00 	lea    0x0(,%eax,4),%edx
 8049342:	8b 45 f4             	mov    -0xc(%ebp),%eax
 8049345:	01 d0                	add    %edx,%eax
 8049347:	8b 10                	mov    (%eax),%edx
 8049349:	8b 45 f0             	mov    -0x10(%ebp),%eax
 804934c:	01 d0                	add    %edx,%eax
 804934e:	83 ec 08             	sub    $0x8,%esp
 8049351:	50                   	push   %eax
 8049352:	ff 75 e4             	pushl  -0x1c(%ebp)
 8049355:	e8 f2 fc ff ff       	call   804904c <launcher>
 804935a:	83 c4 10             	add    $0x10,%esp
 804935d:	83 45 e8 01          	addl   $0x1,-0x18(%ebp)
 8049361:	8b 45 e8             	mov    -0x18(%ebp),%eax
 8049364:	3b 45 ec             	cmp    -0x14(%ebp),%eax
 8049367:	7c cf                	jl     8049338 <main+0x217>
 8049369:	b8 00 00 00 00       	mov    $0x0,%eax
 804936e:	8d 65 f8             	lea    -0x8(%ebp),%esp
 8049371:	59                   	pop    %ecx
 8049372:	5b                   	pop    %ebx
 8049373:	5d                   	pop    %ebp
 8049374:	8d 61 fc             	lea    -0x4(%ecx),%esp
 8049377:	c3                   	ret    

08049378 <getbuf>:
 8049378:	55                   	push   %ebp
 8049379:	89 e5                	mov    %esp,%ebp
 804937b:	83 ec 28             	sub    $0x28,%esp
 804937e:	83 ec 0c             	sub    $0xc,%esp
 8049381:	8d 45 d8             	lea    -0x28(%ebp),%eax
 8049384:	50                   	push   %eax
 8049385:	e8 9e fa ff ff       	call   8048e28 <Gets>
 804938a:	83 c4 10             	add    $0x10,%esp
 804938d:	b8 01 00 00 00       	mov    $0x1,%eax
 8049392:	c9                   	leave  
 8049393:	c3                   	ret    

08049394 <getbufn>:
 8049394:	55                   	push   %ebp
 8049395:	89 e5                	mov    %esp,%ebp
 8049397:	81 ec 08 02 00 00    	sub    $0x208,%esp
 804939d:	83 ec 0c             	sub    $0xc,%esp
 80493a0:	8d 85 f8 fd ff ff    	lea    -0x208(%ebp),%eax
 80493a6:	50                   	push   %eax
 80493a7:	e8 7c fa ff ff       	call   8048e28 <Gets>
 80493ac:	83 c4 10             	add    $0x10,%esp
 80493af:	b8 01 00 00 00       	mov    $0x1,%eax
 80493b4:	c9                   	leave  
 80493b5:	c3                   	ret    

080493b6 <initialize_bomb>:
 80493b6:	55                   	push   %ebp
 80493b7:	89 e5                	mov    %esp,%ebp
 80493b9:	81 ec 18 24 00 00    	sub    $0x2418,%esp
 80493bf:	65 a1 14 00 00 00    	mov    %gs:0x14,%eax
 80493c5:	89 45 f4             	mov    %eax,-0xc(%ebp)
 80493c8:	31 c0                	xor    %eax,%eax
 80493ca:	c7 85 f0 db ff ff 00 	movl   $0x0,-0x2410(%ebp)
 80493d1:	00 00 00 
 80493d4:	a1 50 e1 04 08       	mov    0x804e150,%eax
 80493d9:	85 c0                	test   %eax,%eax
 80493db:	74 0d                	je     80493ea <initialize_bomb+0x34>
 80493dd:	83 ec 0c             	sub    $0xc,%esp
 80493e0:	6a ff                	push   $0xffffffff
 80493e2:	e8 03 0d 00 00       	call   804a0ea <init_timeout>
 80493e7:	83 c4 10             	add    $0x10,%esp
 80493ea:	a1 4c e1 04 08       	mov    0x804e14c,%eax
 80493ef:	85 c0                	test   %eax,%eax
 80493f1:	0f 84 c0 00 00 00    	je     80494b7 <initialize_bomb+0x101>
 80493f7:	83 ec 08             	sub    $0x8,%esp
 80493fa:	68 00 04 00 00       	push   $0x400
 80493ff:	8d 85 f4 db ff ff    	lea    -0x240c(%ebp),%eax
 8049405:	50                   	push   %eax
 8049406:	e8 45 f5 ff ff       	call   8048950 <gethostname@plt>
 804940b:	83 c4 10             	add    $0x10,%esp
 804940e:	85 c0                	test   %eax,%eax
 8049410:	74 1a                	je     804942c <initialize_bomb+0x76>
 8049412:	83 ec 0c             	sub    $0xc,%esp
 8049415:	68 b0 a8 04 08       	push   $0x804a8b0
 804941a:	e8 41 f5 ff ff       	call   8048960 <puts@plt>
 804941f:	83 c4 10             	add    $0x10,%esp
 8049422:	83 ec 0c             	sub    $0xc,%esp
 8049425:	6a 08                	push   $0x8
 8049427:	e8 44 f5 ff ff       	call   8048970 <exit@plt>
 804942c:	c7 85 ec db ff ff 00 	movl   $0x0,-0x2414(%ebp)
 8049433:	00 00 00 
 8049436:	eb 37                	jmp    804946f <initialize_bomb+0xb9>
 8049438:	8b 85 ec db ff ff    	mov    -0x2414(%ebp),%eax
 804943e:	8b 04 85 e0 d0 04 08 	mov    0x804d0e0(,%eax,4),%eax
 8049445:	83 ec 08             	sub    $0x8,%esp
 8049448:	8d 95 f4 db ff ff    	lea    -0x240c(%ebp),%edx
 804944e:	52                   	push   %edx
 804944f:	50                   	push   %eax
 8049450:	e8 8b f5 ff ff       	call   80489e0 <strcasecmp@plt>
 8049455:	83 c4 10             	add    $0x10,%esp
 8049458:	85 c0                	test   %eax,%eax
 804945a:	75 0c                	jne    8049468 <initialize_bomb+0xb2>
 804945c:	c7 85 f0 db ff ff 01 	movl   $0x1,-0x2410(%ebp)
 8049463:	00 00 00 
 8049466:	eb 18                	jmp    8049480 <initialize_bomb+0xca>
 8049468:	83 85 ec db ff ff 01 	addl   $0x1,-0x2414(%ebp)
 804946f:	8b 85 ec db ff ff    	mov    -0x2414(%ebp),%eax
 8049475:	8b 04 85 e0 d0 04 08 	mov    0x804d0e0(,%eax,4),%eax
 804947c:	85 c0                	test   %eax,%eax
 804947e:	75 b8                	jne    8049438 <initialize_bomb+0x82>
 8049480:	83 ec 0c             	sub    $0xc,%esp
 8049483:	8d 85 f4 df ff ff    	lea    -0x200c(%ebp),%eax
 8049489:	50                   	push   %eax
 804948a:	e8 9a 0c 00 00       	call   804a129 <init_driver>
 804948f:	83 c4 10             	add    $0x10,%esp
 8049492:	85 c0                	test   %eax,%eax
 8049494:	79 21                	jns    80494b7 <initialize_bomb+0x101>
 8049496:	83 ec 08             	sub    $0x8,%esp
 8049499:	8d 85 f4 df ff ff    	lea    -0x200c(%ebp),%eax
 804949f:	50                   	push   %eax
 80494a0:	68 e8 a8 04 08       	push   $0x804a8e8
 80494a5:	e8 d6 f3 ff ff       	call   8048880 <printf@plt>
 80494aa:	83 c4 10             	add    $0x10,%esp
 80494ad:	83 ec 0c             	sub    $0xc,%esp
 80494b0:	6a 08                	push   $0x8
 80494b2:	e8 b9 f4 ff ff       	call   8048970 <exit@plt>
 80494b7:	90                   	nop
 80494b8:	8b 45 f4             	mov    -0xc(%ebp),%eax
 80494bb:	65 33 05 14 00 00 00 	xor    %gs:0x14,%eax
 80494c2:	74 05                	je     80494c9 <initialize_bomb+0x113>
 80494c4:	e8 17 f4 ff ff       	call   80488e0 <__stack_chk_fail@plt>
 80494c9:	c9                   	leave  
 80494ca:	c3                   	ret    

080494cb <validate>:
 80494cb:	55                   	push   %ebp
 80494cc:	89 e5                	mov    %esp,%ebp
 80494ce:	81 ec 18 40 00 00    	sub    $0x4018,%esp
 80494d4:	65 a1 14 00 00 00    	mov    %gs:0x14,%eax
 80494da:	89 45 f4             	mov    %eax,-0xc(%ebp)
 80494dd:	31 c0                	xor    %eax,%eax
 80494df:	a1 48 e1 04 08       	mov    0x804e148,%eax
 80494e4:	85 c0                	test   %eax,%eax
 80494e6:	75 15                	jne    80494fd <validate+0x32>
 80494e8:	83 ec 0c             	sub    $0xc,%esp
 80494eb:	68 00 a9 04 08       	push   $0x804a900
 80494f0:	e8 6b f4 ff ff       	call   8048960 <puts@plt>
 80494f5:	83 c4 10             	add    $0x10,%esp
 80494f8:	e9 3c 01 00 00       	jmp    8049639 <validate+0x16e>
 80494fd:	83 7d 08 00          	cmpl   $0x0,0x8(%ebp)
 8049501:	78 06                	js     8049509 <validate+0x3e>
 8049503:	83 7d 08 04          	cmpl   $0x4,0x8(%ebp)
 8049507:	7e 15                	jle    804951e <validate+0x53>
 8049509:	83 ec 0c             	sub    $0xc,%esp
 804950c:	68 2c a9 04 08       	push   $0x804a92c
 8049511:	e8 4a f4 ff ff       	call   8048960 <puts@plt>
 8049516:	83 c4 10             	add    $0x10,%esp
 8049519:	e9 1b 01 00 00       	jmp    8049639 <validate+0x16e>
 804951e:	c7 05 5c e1 04 08 01 	movl   $0x1,0x804e15c
 8049525:	00 00 00 
 8049528:	8b 45 08             	mov    0x8(%ebp),%eax
 804952b:	8b 04 85 e0 e0 04 08 	mov    0x804e0e0(,%eax,4),%eax
 8049532:	8d 50 ff             	lea    -0x1(%eax),%edx
 8049535:	8b 45 08             	mov    0x8(%ebp),%eax
 8049538:	89 14 85 e0 e0 04 08 	mov    %edx,0x804e0e0(,%eax,4)
 804953f:	8b 45 08             	mov    0x8(%ebp),%eax
 8049542:	8b 04 85 e0 e0 04 08 	mov    0x804e0e0(,%eax,4),%eax
 8049549:	85 c0                	test   %eax,%eax
 804954b:	7e 15                	jle    8049562 <validate+0x97>
 804954d:	83 ec 0c             	sub    $0xc,%esp
 8049550:	68 52 a9 04 08       	push   $0x804a952
 8049555:	e8 06 f4 ff ff       	call   8048960 <puts@plt>
 804955a:	83 c4 10             	add    $0x10,%esp
 804955d:	e9 d7 00 00 00       	jmp    8049639 <validate+0x16e>
 8049562:	83 ec 0c             	sub    $0xc,%esp
 8049565:	68 5d a9 04 08       	push   $0x804a95d
 804956a:	e8 f1 f3 ff ff       	call   8048960 <puts@plt>
 804956f:	83 c4 10             	add    $0x10,%esp
 8049572:	a1 4c e1 04 08       	mov    0x804e14c,%eax
 8049577:	85 c0                	test   %eax,%eax
 8049579:	0f 84 aa 00 00 00    	je     8049629 <validate+0x15e>
 804957f:	83 ec 0c             	sub    $0xc,%esp
 8049582:	68 a0 e1 04 08       	push   $0x804e1a0
 8049587:	e8 14 f4 ff ff       	call   80489a0 <strlen@plt>
 804958c:	83 c4 10             	add    $0x10,%esp
 804958f:	83 c0 20             	add    $0x20,%eax
 8049592:	3d 00 20 00 00       	cmp    $0x2000,%eax
 8049597:	76 15                	jbe    80495ae <validate+0xe3>
 8049599:	83 ec 0c             	sub    $0xc,%esp
 804959c:	68 64 a9 04 08       	push   $0x804a964
 80495a1:	e8 ba f3 ff ff       	call   8048960 <puts@plt>
 80495a6:	83 c4 10             	add    $0x10,%esp
 80495a9:	e9 8b 00 00 00       	jmp    8049639 <validate+0x16e>
 80495ae:	a1 58 e1 04 08       	mov    0x804e158,%eax
 80495b3:	83 ec 0c             	sub    $0xc,%esp
 80495b6:	68 a0 e1 04 08       	push   $0x804e1a0
 80495bb:	50                   	push   %eax
 80495bc:	ff 75 08             	pushl  0x8(%ebp)
 80495bf:	68 9b a9 04 08       	push   $0x804a99b
 80495c4:	8d 85 f4 bf ff ff    	lea    -0x400c(%ebp),%eax
 80495ca:	50                   	push   %eax
 80495cb:	e8 70 f4 ff ff       	call   8048a40 <sprintf@plt>
 80495d0:	83 c4 20             	add    $0x20,%esp
 80495d3:	a1 48 e1 04 08       	mov    0x804e148,%eax
 80495d8:	8d 95 f4 df ff ff    	lea    -0x200c(%ebp),%edx
 80495de:	52                   	push   %edx
 80495df:	6a 00                	push   $0x0
 80495e1:	8d 95 f4 bf ff ff    	lea    -0x400c(%ebp),%edx
 80495e7:	52                   	push   %edx
 80495e8:	50                   	push   %eax
 80495e9:	e8 03 0d 00 00       	call   804a2f1 <driver_post>
 80495ee:	83 c4 10             	add    $0x10,%esp
 80495f1:	89 85 f0 bf ff ff    	mov    %eax,-0x4010(%ebp)
 80495f7:	83 bd f0 bf ff ff 00 	cmpl   $0x0,-0x4010(%ebp)
 80495fe:	75 12                	jne    8049612 <validate+0x147>
 8049600:	83 ec 0c             	sub    $0xc,%esp
 8049603:	68 a4 a9 04 08       	push   $0x804a9a4
 8049608:	e8 53 f3 ff ff       	call   8048960 <puts@plt>
 804960d:	83 c4 10             	add    $0x10,%esp
 8049610:	eb 17                	jmp    8049629 <validate+0x15e>
 8049612:	83 ec 08             	sub    $0x8,%esp
 8049615:	8d 85 f4 df ff ff    	lea    -0x200c(%ebp),%eax
 804961b:	50                   	push   %eax
 804961c:	68 d4 a9 04 08       	push   $0x804a9d4
 8049621:	e8 5a f2 ff ff       	call   8048880 <printf@plt>
 8049626:	83 c4 10             	add    $0x10,%esp
 8049629:	83 ec 0c             	sub    $0xc,%esp
 804962c:	68 12 aa 04 08       	push   $0x804aa12
 8049631:	e8 2a f3 ff ff       	call   8048960 <puts@plt>
 8049636:	83 c4 10             	add    $0x10,%esp
 8049639:	8b 45 f4             	mov    -0xc(%ebp),%eax
 804963c:	65 33 05 14 00 00 00 	xor    %gs:0x14,%eax
 8049643:	74 05                	je     804964a <validate+0x17f>
 8049645:	e8 96 f2 ff ff       	call   80488e0 <__stack_chk_fail@plt>
 804964a:	c9                   	leave  
 804964b:	c3                   	ret    

0804964c <sigalrm_handler>:
 804964c:	55                   	push   %ebp
 804964d:	89 e5                	mov    %esp,%ebp
 804964f:	83 ec 08             	sub    $0x8,%esp
 8049652:	83 ec 08             	sub    $0x8,%esp
 8049655:	6a 02                	push   $0x2
 8049657:	68 1c aa 04 08       	push   $0x804aa1c
 804965c:	e8 1f f2 ff ff       	call   8048880 <printf@plt>
 8049661:	83 c4 10             	add    $0x10,%esp
 8049664:	83 ec 0c             	sub    $0xc,%esp
 8049667:	6a 01                	push   $0x1
 8049669:	e8 02 f3 ff ff       	call   8048970 <exit@plt>

0804966e <rio_readinitb>:
 804966e:	55                   	push   %ebp
 804966f:	89 e5                	mov    %esp,%ebp
 8049671:	8b 45 08             	mov    0x8(%ebp),%eax
 8049674:	8b 55 0c             	mov    0xc(%ebp),%edx
 8049677:	89 10                	mov    %edx,(%eax)
 8049679:	8b 45 08             	mov    0x8(%ebp),%eax
 804967c:	c7 40 04 00 00 00 00 	movl   $0x0,0x4(%eax)
 8049683:	8b 45 08             	mov    0x8(%ebp),%eax
 8049686:	8d 50 0c             	lea    0xc(%eax),%edx
 8049689:	8b 45 08             	mov    0x8(%ebp),%eax
 804968c:	89 50 08             	mov    %edx,0x8(%eax)
 804968f:	90                   	nop
 8049690:	5d                   	pop    %ebp
 8049691:	c3                   	ret    

08049692 <rio_read>:
 8049692:	55                   	push   %ebp
 8049693:	89 e5                	mov    %esp,%ebp
 8049695:	83 ec 18             	sub    $0x18,%esp
 8049698:	eb 5f                	jmp    80496f9 <rio_read+0x67>
 804969a:	8b 45 08             	mov    0x8(%ebp),%eax
 804969d:	8d 50 0c             	lea    0xc(%eax),%edx
 80496a0:	8b 45 08             	mov    0x8(%ebp),%eax
 80496a3:	8b 00                	mov    (%eax),%eax
 80496a5:	83 ec 04             	sub    $0x4,%esp
 80496a8:	68 00 20 00 00       	push   $0x2000
 80496ad:	52                   	push   %edx
 80496ae:	50                   	push   %eax
 80496af:	e8 ac f1 ff ff       	call   8048860 <read@plt>
 80496b4:	83 c4 10             	add    $0x10,%esp
 80496b7:	89 c2                	mov    %eax,%edx
 80496b9:	8b 45 08             	mov    0x8(%ebp),%eax
 80496bc:	89 50 04             	mov    %edx,0x4(%eax)
 80496bf:	8b 45 08             	mov    0x8(%ebp),%eax
 80496c2:	8b 40 04             	mov    0x4(%eax),%eax
 80496c5:	85 c0                	test   %eax,%eax
 80496c7:	79 13                	jns    80496dc <rio_read+0x4a>
 80496c9:	e8 42 f3 ff ff       	call   8048a10 <__errno_location@plt>
 80496ce:	8b 00                	mov    (%eax),%eax
 80496d0:	83 f8 04             	cmp    $0x4,%eax
 80496d3:	74 24                	je     80496f9 <rio_read+0x67>
 80496d5:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 80496da:	eb 7f                	jmp    804975b <rio_read+0xc9>
 80496dc:	8b 45 08             	mov    0x8(%ebp),%eax
 80496df:	8b 40 04             	mov    0x4(%eax),%eax
 80496e2:	85 c0                	test   %eax,%eax
 80496e4:	75 07                	jne    80496ed <rio_read+0x5b>
 80496e6:	b8 00 00 00 00       	mov    $0x0,%eax
 80496eb:	eb 6e                	jmp    804975b <rio_read+0xc9>
 80496ed:	8b 45 08             	mov    0x8(%ebp),%eax
 80496f0:	8d 50 0c             	lea    0xc(%eax),%edx
 80496f3:	8b 45 08             	mov    0x8(%ebp),%eax
 80496f6:	89 50 08             	mov    %edx,0x8(%eax)
 80496f9:	8b 45 08             	mov    0x8(%ebp),%eax
 80496fc:	8b 40 04             	mov    0x4(%eax),%eax
 80496ff:	85 c0                	test   %eax,%eax
 8049701:	7e 97                	jle    804969a <rio_read+0x8>
 8049703:	8b 45 10             	mov    0x10(%ebp),%eax
 8049706:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8049709:	8b 45 08             	mov    0x8(%ebp),%eax
 804970c:	8b 40 04             	mov    0x4(%eax),%eax
 804970f:	3b 45 10             	cmp    0x10(%ebp),%eax
 8049712:	73 09                	jae    804971d <rio_read+0x8b>
 8049714:	8b 45 08             	mov    0x8(%ebp),%eax
 8049717:	8b 40 04             	mov    0x4(%eax),%eax
 804971a:	89 45 f4             	mov    %eax,-0xc(%ebp)
 804971d:	8b 55 f4             	mov    -0xc(%ebp),%edx
 8049720:	8b 45 08             	mov    0x8(%ebp),%eax
 8049723:	8b 40 08             	mov    0x8(%eax),%eax
 8049726:	83 ec 04             	sub    $0x4,%esp
 8049729:	52                   	push   %edx
 804972a:	50                   	push   %eax
 804972b:	ff 75 0c             	pushl  0xc(%ebp)
 804972e:	e8 6d f1 ff ff       	call   80488a0 <memcpy@plt>
 8049733:	83 c4 10             	add    $0x10,%esp
 8049736:	8b 45 08             	mov    0x8(%ebp),%eax
 8049739:	8b 50 08             	mov    0x8(%eax),%edx
 804973c:	8b 45 f4             	mov    -0xc(%ebp),%eax
 804973f:	01 c2                	add    %eax,%edx
 8049741:	8b 45 08             	mov    0x8(%ebp),%eax
 8049744:	89 50 08             	mov    %edx,0x8(%eax)
 8049747:	8b 45 08             	mov    0x8(%ebp),%eax
 804974a:	8b 40 04             	mov    0x4(%eax),%eax
 804974d:	2b 45 f4             	sub    -0xc(%ebp),%eax
 8049750:	89 c2                	mov    %eax,%edx
 8049752:	8b 45 08             	mov    0x8(%ebp),%eax
 8049755:	89 50 04             	mov    %edx,0x4(%eax)
 8049758:	8b 45 f4             	mov    -0xc(%ebp),%eax
 804975b:	c9                   	leave  
 804975c:	c3                   	ret    

0804975d <rio_readlineb>:
 804975d:	55                   	push   %ebp
 804975e:	89 e5                	mov    %esp,%ebp
 8049760:	83 ec 38             	sub    $0x38,%esp
 8049763:	8b 45 08             	mov    0x8(%ebp),%eax
 8049766:	89 45 d4             	mov    %eax,-0x2c(%ebp)
 8049769:	8b 45 0c             	mov    0xc(%ebp),%eax
 804976c:	89 45 d0             	mov    %eax,-0x30(%ebp)
 804976f:	65 a1 14 00 00 00    	mov    %gs:0x14,%eax
 8049775:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8049778:	31 c0                	xor    %eax,%eax
 804977a:	8b 45 d0             	mov    -0x30(%ebp),%eax
 804977d:	89 45 ec             	mov    %eax,-0x14(%ebp)
 8049780:	c7 45 e8 01 00 00 00 	movl   $0x1,-0x18(%ebp)
 8049787:	eb 54                	jmp    80497dd <rio_readlineb+0x80>
 8049789:	83 ec 04             	sub    $0x4,%esp
 804978c:	6a 01                	push   $0x1
 804978e:	8d 45 e7             	lea    -0x19(%ebp),%eax
 8049791:	50                   	push   %eax
 8049792:	ff 75 d4             	pushl  -0x2c(%ebp)
 8049795:	e8 f8 fe ff ff       	call   8049692 <rio_read>
 804979a:	83 c4 10             	add    $0x10,%esp
 804979d:	89 45 f0             	mov    %eax,-0x10(%ebp)
 80497a0:	83 7d f0 01          	cmpl   $0x1,-0x10(%ebp)
 80497a4:	75 19                	jne    80497bf <rio_readlineb+0x62>
 80497a6:	8b 45 ec             	mov    -0x14(%ebp),%eax
 80497a9:	8d 50 01             	lea    0x1(%eax),%edx
 80497ac:	89 55 ec             	mov    %edx,-0x14(%ebp)
 80497af:	0f b6 55 e7          	movzbl -0x19(%ebp),%edx
 80497b3:	88 10                	mov    %dl,(%eax)
 80497b5:	0f b6 45 e7          	movzbl -0x19(%ebp),%eax
 80497b9:	3c 0a                	cmp    $0xa,%al
 80497bb:	75 1c                	jne    80497d9 <rio_readlineb+0x7c>
 80497bd:	eb 29                	jmp    80497e8 <rio_readlineb+0x8b>
 80497bf:	83 7d f0 00          	cmpl   $0x0,-0x10(%ebp)
 80497c3:	75 0d                	jne    80497d2 <rio_readlineb+0x75>
 80497c5:	83 7d e8 01          	cmpl   $0x1,-0x18(%ebp)
 80497c9:	75 1c                	jne    80497e7 <rio_readlineb+0x8a>
 80497cb:	b8 00 00 00 00       	mov    $0x0,%eax
 80497d0:	eb 1f                	jmp    80497f1 <rio_readlineb+0x94>
 80497d2:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 80497d7:	eb 18                	jmp    80497f1 <rio_readlineb+0x94>
 80497d9:	83 45 e8 01          	addl   $0x1,-0x18(%ebp)
 80497dd:	8b 45 e8             	mov    -0x18(%ebp),%eax
 80497e0:	3b 45 10             	cmp    0x10(%ebp),%eax
 80497e3:	72 a4                	jb     8049789 <rio_readlineb+0x2c>
 80497e5:	eb 01                	jmp    80497e8 <rio_readlineb+0x8b>
 80497e7:	90                   	nop
 80497e8:	8b 45 ec             	mov    -0x14(%ebp),%eax
 80497eb:	c6 00 00             	movb   $0x0,(%eax)
 80497ee:	8b 45 e8             	mov    -0x18(%ebp),%eax
 80497f1:	8b 4d f4             	mov    -0xc(%ebp),%ecx
 80497f4:	65 33 0d 14 00 00 00 	xor    %gs:0x14,%ecx
 80497fb:	74 05                	je     8049802 <rio_readlineb+0xa5>
 80497fd:	e8 de f0 ff ff       	call   80488e0 <__stack_chk_fail@plt>
 8049802:	c9                   	leave  
 8049803:	c3                   	ret    

08049804 <rio_writen>:
 8049804:	55                   	push   %ebp
 8049805:	89 e5                	mov    %esp,%ebp
 8049807:	83 ec 18             	sub    $0x18,%esp
 804980a:	8b 45 10             	mov    0x10(%ebp),%eax
 804980d:	89 45 ec             	mov    %eax,-0x14(%ebp)
 8049810:	8b 45 0c             	mov    0xc(%ebp),%eax
 8049813:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8049816:	eb 45                	jmp    804985d <rio_writen+0x59>
 8049818:	83 ec 04             	sub    $0x4,%esp
 804981b:	ff 75 ec             	pushl  -0x14(%ebp)
 804981e:	ff 75 f4             	pushl  -0xc(%ebp)
 8049821:	ff 75 08             	pushl  0x8(%ebp)
 8049824:	e8 97 f1 ff ff       	call   80489c0 <write@plt>
 8049829:	83 c4 10             	add    $0x10,%esp
 804982c:	89 45 f0             	mov    %eax,-0x10(%ebp)
 804982f:	83 7d f0 00          	cmpl   $0x0,-0x10(%ebp)
 8049833:	7f 1c                	jg     8049851 <rio_writen+0x4d>
 8049835:	e8 d6 f1 ff ff       	call   8048a10 <__errno_location@plt>
 804983a:	8b 00                	mov    (%eax),%eax
 804983c:	83 f8 04             	cmp    $0x4,%eax
 804983f:	75 09                	jne    804984a <rio_writen+0x46>
 8049841:	c7 45 f0 00 00 00 00 	movl   $0x0,-0x10(%ebp)
 8049848:	eb 07                	jmp    8049851 <rio_writen+0x4d>
 804984a:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 804984f:	eb 15                	jmp    8049866 <rio_writen+0x62>
 8049851:	8b 45 f0             	mov    -0x10(%ebp),%eax
 8049854:	29 45 ec             	sub    %eax,-0x14(%ebp)
 8049857:	8b 45 f0             	mov    -0x10(%ebp),%eax
 804985a:	01 45 f4             	add    %eax,-0xc(%ebp)
 804985d:	83 7d ec 00          	cmpl   $0x0,-0x14(%ebp)
 8049861:	75 b5                	jne    8049818 <rio_writen+0x14>
 8049863:	8b 45 10             	mov    0x10(%ebp),%eax
 8049866:	c9                   	leave  
 8049867:	c3                   	ret    

08049868 <urlencode>:
 8049868:	55                   	push   %ebp
 8049869:	89 e5                	mov    %esp,%ebp
 804986b:	83 ec 28             	sub    $0x28,%esp
 804986e:	8b 45 08             	mov    0x8(%ebp),%eax
 8049871:	89 45 e4             	mov    %eax,-0x1c(%ebp)
 8049874:	8b 45 0c             	mov    0xc(%ebp),%eax
 8049877:	89 45 e0             	mov    %eax,-0x20(%ebp)
 804987a:	65 a1 14 00 00 00    	mov    %gs:0x14,%eax
 8049880:	89 45 f4             	mov    %eax,-0xc(%ebp)
 8049883:	31 c0                	xor    %eax,%eax
 8049885:	83 ec 0c             	sub    $0xc,%esp
 8049888:	ff 75 e4             	pushl  -0x1c(%ebp)
 804988b:	e8 10 f1 ff ff       	call   80489a0 <strlen@plt>
 8049890:	83 c4 10             	add    $0x10,%esp
 8049893:	89 45 e8             	mov    %eax,-0x18(%ebp)
 8049896:	e9 08 01 00 00       	jmp    80499a3 <urlencode+0x13b>
 804989b:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 804989e:	0f b6 00             	movzbl (%eax),%eax
 80498a1:	3c 2a                	cmp    $0x2a,%al
 80498a3:	74 5a                	je     80498ff <urlencode+0x97>
 80498a5:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498a8:	0f b6 00             	movzbl (%eax),%eax
 80498ab:	3c 2d                	cmp    $0x2d,%al
 80498ad:	74 50                	je     80498ff <urlencode+0x97>
 80498af:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498b2:	0f b6 00             	movzbl (%eax),%eax
 80498b5:	3c 2e                	cmp    $0x2e,%al
 80498b7:	74 46                	je     80498ff <urlencode+0x97>
 80498b9:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498bc:	0f b6 00             	movzbl (%eax),%eax
 80498bf:	3c 5f                	cmp    $0x5f,%al
 80498c1:	74 3c                	je     80498ff <urlencode+0x97>
 80498c3:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498c6:	0f b6 00             	movzbl (%eax),%eax
 80498c9:	3c 2f                	cmp    $0x2f,%al
 80498cb:	76 0a                	jbe    80498d7 <urlencode+0x6f>
 80498cd:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498d0:	0f b6 00             	movzbl (%eax),%eax
 80498d3:	3c 39                	cmp    $0x39,%al
 80498d5:	76 28                	jbe    80498ff <urlencode+0x97>
 80498d7:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498da:	0f b6 00             	movzbl (%eax),%eax
 80498dd:	3c 40                	cmp    $0x40,%al
 80498df:	76 0a                	jbe    80498eb <urlencode+0x83>
 80498e1:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498e4:	0f b6 00             	movzbl (%eax),%eax
 80498e7:	3c 5a                	cmp    $0x5a,%al
 80498e9:	76 14                	jbe    80498ff <urlencode+0x97>
 80498eb:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498ee:	0f b6 00             	movzbl (%eax),%eax
 80498f1:	3c 60                	cmp    $0x60,%al
 80498f3:	76 20                	jbe    8049915 <urlencode+0xad>
 80498f5:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 80498f8:	0f b6 00             	movzbl (%eax),%eax
 80498fb:	3c 7a                	cmp    $0x7a,%al
 80498fd:	77 16                	ja     8049915 <urlencode+0xad>
 80498ff:	8b 45 e0             	mov    -0x20(%ebp),%eax
 8049902:	8d 50 01             	lea    0x1(%eax),%edx
 8049905:	89 55 e0             	mov    %edx,-0x20(%ebp)
 8049908:	8b 55 e4             	mov    -0x1c(%ebp),%edx
 804990b:	0f b6 12             	movzbl (%edx),%edx
 804990e:	88 10                	mov    %dl,(%eax)
 8049910:	e9 8a 00 00 00       	jmp    804999f <urlencode+0x137>
 8049915:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 8049918:	0f b6 00             	movzbl (%eax),%eax
 804991b:	3c 20                	cmp    $0x20,%al
 804991d:	75 0e                	jne    804992d <urlencode+0xc5>
 804991f:	8b 45 e0             	mov    -0x20(%ebp),%eax
 8049922:	8d 50 01             	lea    0x1(%eax),%edx
 8049925:	89 55 e0             	mov    %edx,-0x20(%ebp)
 8049928:	c6 00 2b             	movb   $0x2b,(%eax)
 804992b:	eb 72                	jmp    804999f <urlencode+0x137>
 804992d:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 8049930:	0f b6 00             	movzbl (%eax),%eax
 8049933:	3c 1f                	cmp    $0x1f,%al
 8049935:	76 0a                	jbe    8049941 <urlencode+0xd9>
 8049937:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 804993a:	0f b6 00             	movzbl (%eax),%eax
 804993d:	84 c0                	test   %al,%al
 804993f:	79 0a                	jns    804994b <urlencode+0xe3>
 8049941:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 8049944:	0f b6 00             	movzbl (%eax),%eax
 8049947:	3c 09                	cmp    $0x9,%al
 8049949:	75 4d                	jne    8049998 <urlencode+0x130>
 804994b:	8b 45 e4             	mov    -0x1c(%ebp),%eax
 804994e:	0f b6 00             	movzbl (%eax),%eax
 8049951:	0f b6 c0             	movzbl %al,%eax
 8049954:	83 ec 04             	sub    $0x4,%esp
 8049957:	50                   	push   %eax
 8049958:	68 40 aa 04 08       	push   $0x804aa40
 804995d:	8d 45 ec             	lea    -0x14(%ebp),%eax
 8049960:	50                   	push   %eax
 8049961:	e8 da f0 ff ff       	call   8048a40 <sprintf@plt>
 8049966:	83 c4 10             	add    $0x10,%esp
 8049969:	8b 45 e0             	mov    -0x20(%ebp),%eax
 804996c:	8d 50 01             	lea    0x1(%eax),%edx
 804996f:	89 55 e0             	mov    %edx,-0x20(%ebp)
 8049972:	0f b6 55 ec          	movzbl -0x14(%ebp),%edx
 8049976:	88 10                	mov    %dl,(%eax)
 8049978:	8b 45 e0             	mov    -0x20(%ebp),%eax
 804997b:	8d 50 01             	lea    0x1(%eax),%edx
 804997e:	89 55 e0             	mov    %edx,-0x20(%ebp)
 8049981:	0f b6 55 ed          	movzbl -0x13(%ebp),%edx
 8049985:	88 10                	mov    %dl,(%eax)
 8049987:	8b 45 e0             	mov    -0x20(%ebp),%eax
 804998a:	8d 50 01             	lea    0x1(%eax),%edx
 804998d:	89 55 e0             	mov    %edx,-0x20(%ebp)
 8049990:	0f b6 55 ee          	movzbl -0x12(%ebp),%edx
 8049994:	88 10                	mov    %dl,(%eax)
 8049996:	eb 07                	jmp    804999f <urlencode+0x137>
 8049998:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 804999d:	eb 1a                	jmp    80499b9 <urlencode+0x151>
 804999f:	83 45 e4 01          	addl   $0x1,-0x1c(%ebp)
 80499a3:	8b 45 e8             	mov    -0x18(%ebp),%eax
 80499a6:	8d 50 ff             	lea    -0x1(%eax),%edx
 80499a9:	89 55 e8             	mov    %edx,-0x18(%ebp)
 80499ac:	85 c0                	test   %eax,%eax
 80499ae:	0f 85 e7 fe ff ff    	jne    804989b <urlencode+0x33>
 80499b4:	b8 00 00 00 00       	mov    $0x0,%eax
 80499b9:	8b 4d f4             	mov    -0xc(%ebp),%ecx
 80499bc:	65 33 0d 14 00 00 00 	xor    %gs:0x14,%ecx
 80499c3:	74 05                	je     80499ca <urlencode+0x162>
 80499c5:	e8 16 ef ff ff       	call   80488e0 <__stack_chk_fail@plt>
 80499ca:	c9                   	leave  
 80499cb:	c3                   	ret    

080499cc <submitr>:
 80499cc:	55                   	push   %ebp
 80499cd:	89 e5                	mov    %esp,%ebp
 80499cf:	57                   	push   %edi
 80499d0:	56                   	push   %esi
 80499d1:	53                   	push   %ebx
 80499d2:	81 ec 6c a0 00 00    	sub    $0xa06c,%esp
 80499d8:	8b 45 08             	mov    0x8(%ebp),%eax
 80499db:	89 85 a4 5f ff ff    	mov    %eax,-0xa05c(%ebp)
 80499e1:	8b 45 10             	mov    0x10(%ebp),%eax
 80499e4:	89 85 a0 5f ff ff    	mov    %eax,-0xa060(%ebp)
 80499ea:	8b 45 14             	mov    0x14(%ebp),%eax
 80499ed:	89 85 9c 5f ff ff    	mov    %eax,-0xa064(%ebp)
 80499f3:	8b 45 18             	mov    0x18(%ebp),%eax
 80499f6:	89 85 98 5f ff ff    	mov    %eax,-0xa068(%ebp)
 80499fc:	8b 45 1c             	mov    0x1c(%ebp),%eax
 80499ff:	89 85 94 5f ff ff    	mov    %eax,-0xa06c(%ebp)
 8049a05:	8b 45 20             	mov    0x20(%ebp),%eax
 8049a08:	89 85 90 5f ff ff    	mov    %eax,-0xa070(%ebp)
 8049a0e:	65 a1 14 00 00 00    	mov    %gs:0x14,%eax
 8049a14:	89 45 e4             	mov    %eax,-0x1c(%ebp)
 8049a17:	31 c0                	xor    %eax,%eax
 8049a19:	c7 85 b4 5f ff ff 00 	movl   $0x0,-0xa04c(%ebp)
 8049a20:	00 00 00 
 8049a23:	83 ec 04             	sub    $0x4,%esp
 8049a26:	6a 00                	push   $0x0
 8049a28:	6a 01                	push   $0x1
 8049a2a:	6a 02                	push   $0x2
 8049a2c:	e8 1f f0 ff ff       	call   8048a50 <socket@plt>
 8049a31:	83 c4 10             	add    $0x10,%esp
 8049a34:	89 85 b8 5f ff ff    	mov    %eax,-0xa048(%ebp)
 8049a3a:	83 bd b8 5f ff ff 00 	cmpl   $0x0,-0xa048(%ebp)
 8049a41:	79 54                	jns    8049a97 <submitr+0xcb>
 8049a43:	8b 85 90 5f ff ff    	mov    -0xa070(%ebp),%eax
 8049a49:	c7 00 45 72 72 6f    	movl   $0x6f727245,(%eax)
 8049a4f:	c7 40 04 72 3a 20 43 	movl   $0x43203a72,0x4(%eax)
 8049a56:	c7 40 08 6c 69 65 6e 	movl   $0x6e65696c,0x8(%eax)
 8049a5d:	c7 40 0c 74 20 75 6e 	movl   $0x6e752074,0xc(%eax)
 8049a64:	c7 40 10 61 62 6c 65 	movl   $0x656c6261,0x10(%eax)
 8049a6b:	c7 40 14 20 74 6f 20 	movl   $0x206f7420,0x14(%eax)
 8049a72:	c7 40 18 63 72 65 61 	movl   $0x61657263,0x18(%eax)
 8049a79:	c7 40 1c 74 65 20 73 	movl   $0x73206574,0x1c(%eax)
 8049a80:	c7 40 20 6f 63 6b 65 	movl   $0x656b636f,0x20(%eax)
 8049a87:	66 c7 40 24 74 00    	movw   $0x74,0x24(%eax)
 8049a8d:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049a92:	e9 3a 06 00 00       	jmp    804a0d1 <submitr+0x705>
 8049a97:	83 ec 0c             	sub    $0xc,%esp
 8049a9a:	ff b5 a4 5f ff ff    	pushl  -0xa05c(%ebp)
 8049aa0:	e8 cb ef ff ff       	call   8048a70 <gethostbyname@plt>
 8049aa5:	83 c4 10             	add    $0x10,%esp
 8049aa8:	89 85 bc 5f ff ff    	mov    %eax,-0xa044(%ebp)
 8049aae:	83 bd bc 5f ff ff 00 	cmpl   $0x0,-0xa044(%ebp)
 8049ab5:	75 37                	jne    8049aee <submitr+0x122>
 8049ab7:	83 ec 04             	sub    $0x4,%esp
 8049aba:	ff b5 a4 5f ff ff    	pushl  -0xa05c(%ebp)
 8049ac0:	68 48 aa 04 08       	push   $0x804aa48
 8049ac5:	ff b5 90 5f ff ff    	pushl  -0xa070(%ebp)
 8049acb:	e8 70 ef ff ff       	call   8048a40 <sprintf@plt>
 8049ad0:	83 c4 10             	add    $0x10,%esp
 8049ad3:	83 ec 0c             	sub    $0xc,%esp
 8049ad6:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049adc:	e8 af ef ff ff       	call   8048a90 <close@plt>
 8049ae1:	83 c4 10             	add    $0x10,%esp
 8049ae4:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049ae9:	e9 e3 05 00 00       	jmp    804a0d1 <submitr+0x705>
 8049aee:	83 ec 08             	sub    $0x8,%esp
 8049af1:	6a 10                	push   $0x10
 8049af3:	8d 85 c8 5f ff ff    	lea    -0xa038(%ebp),%eax
 8049af9:	50                   	push   %eax
 8049afa:	e8 b1 ed ff ff       	call   80488b0 <bzero@plt>
 8049aff:	83 c4 10             	add    $0x10,%esp
 8049b02:	66 c7 85 c8 5f ff ff 	movw   $0x2,-0xa038(%ebp)
 8049b09:	02 00 
 8049b0b:	8b 85 bc 5f ff ff    	mov    -0xa044(%ebp),%eax
 8049b11:	8b 40 0c             	mov    0xc(%eax),%eax
 8049b14:	89 c2                	mov    %eax,%edx
 8049b16:	8b 85 bc 5f ff ff    	mov    -0xa044(%ebp),%eax
 8049b1c:	8b 40 10             	mov    0x10(%eax),%eax
 8049b1f:	8b 00                	mov    (%eax),%eax
 8049b21:	83 ec 04             	sub    $0x4,%esp
 8049b24:	52                   	push   %edx
 8049b25:	8d 95 c8 5f ff ff    	lea    -0xa038(%ebp),%edx
 8049b2b:	83 c2 04             	add    $0x4,%edx
 8049b2e:	52                   	push   %edx
 8049b2f:	50                   	push   %eax
 8049b30:	e8 eb ed ff ff       	call   8048920 <bcopy@plt>
 8049b35:	83 c4 10             	add    $0x10,%esp
 8049b38:	8b 45 0c             	mov    0xc(%ebp),%eax
 8049b3b:	0f b7 c0             	movzwl %ax,%eax
 8049b3e:	83 ec 0c             	sub    $0xc,%esp
 8049b41:	50                   	push   %eax
 8049b42:	e8 b9 ed ff ff       	call   8048900 <htons@plt>
 8049b47:	83 c4 10             	add    $0x10,%esp
 8049b4a:	66 89 85 ca 5f ff ff 	mov    %ax,-0xa036(%ebp)
 8049b51:	83 ec 04             	sub    $0x4,%esp
 8049b54:	6a 10                	push   $0x10
 8049b56:	8d 85 c8 5f ff ff    	lea    -0xa038(%ebp),%eax
 8049b5c:	50                   	push   %eax
 8049b5d:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049b63:	e8 18 ef ff ff       	call   8048a80 <connect@plt>
 8049b68:	83 c4 10             	add    $0x10,%esp
 8049b6b:	85 c0                	test   %eax,%eax
 8049b6d:	79 37                	jns    8049ba6 <submitr+0x1da>
 8049b6f:	83 ec 04             	sub    $0x4,%esp
 8049b72:	ff b5 a4 5f ff ff    	pushl  -0xa05c(%ebp)
 8049b78:	68 74 aa 04 08       	push   $0x804aa74
 8049b7d:	ff b5 90 5f ff ff    	pushl  -0xa070(%ebp)
 8049b83:	e8 b8 ee ff ff       	call   8048a40 <sprintf@plt>
 8049b88:	83 c4 10             	add    $0x10,%esp
 8049b8b:	83 ec 0c             	sub    $0xc,%esp
 8049b8e:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049b94:	e8 f7 ee ff ff       	call   8048a90 <close@plt>
 8049b99:	83 c4 10             	add    $0x10,%esp
 8049b9c:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049ba1:	e9 2b 05 00 00       	jmp    804a0d1 <submitr+0x705>
 8049ba6:	83 ec 0c             	sub    $0xc,%esp
 8049ba9:	ff b5 94 5f ff ff    	pushl  -0xa06c(%ebp)
 8049baf:	e8 ec ed ff ff       	call   80489a0 <strlen@plt>
 8049bb4:	83 c4 10             	add    $0x10,%esp
 8049bb7:	89 85 c0 5f ff ff    	mov    %eax,-0xa040(%ebp)
 8049bbd:	83 ec 0c             	sub    $0xc,%esp
 8049bc0:	ff b5 a0 5f ff ff    	pushl  -0xa060(%ebp)
 8049bc6:	e8 d5 ed ff ff       	call   80489a0 <strlen@plt>
 8049bcb:	83 c4 10             	add    $0x10,%esp
 8049bce:	89 c3                	mov    %eax,%ebx
 8049bd0:	83 ec 0c             	sub    $0xc,%esp
 8049bd3:	ff b5 9c 5f ff ff    	pushl  -0xa064(%ebp)
 8049bd9:	e8 c2 ed ff ff       	call   80489a0 <strlen@plt>
 8049bde:	83 c4 10             	add    $0x10,%esp
 8049be1:	01 c3                	add    %eax,%ebx
 8049be3:	83 ec 0c             	sub    $0xc,%esp
 8049be6:	ff b5 98 5f ff ff    	pushl  -0xa068(%ebp)
 8049bec:	e8 af ed ff ff       	call   80489a0 <strlen@plt>
 8049bf1:	83 c4 10             	add    $0x10,%esp
 8049bf4:	8d 0c 03             	lea    (%ebx,%eax,1),%ecx
 8049bf7:	8b 95 c0 5f ff ff    	mov    -0xa040(%ebp),%edx
 8049bfd:	89 d0                	mov    %edx,%eax
 8049bff:	01 c0                	add    %eax,%eax
 8049c01:	01 d0                	add    %edx,%eax
 8049c03:	01 c8                	add    %ecx,%eax
 8049c05:	83 e8 80             	sub    $0xffffff80,%eax
 8049c08:	89 85 c4 5f ff ff    	mov    %eax,-0xa03c(%ebp)
 8049c0e:	81 bd c4 5f ff ff 00 	cmpl   $0x2000,-0xa03c(%ebp)
 8049c15:	20 00 00 
 8049c18:	0f 86 82 00 00 00    	jbe    8049ca0 <submitr+0x2d4>
 8049c1e:	8b 85 90 5f ff ff    	mov    -0xa070(%ebp),%eax
 8049c24:	c7 00 45 72 72 6f    	movl   $0x6f727245,(%eax)
 8049c2a:	c7 40 04 72 3a 20 52 	movl   $0x52203a72,0x4(%eax)
 8049c31:	c7 40 08 65 73 75 6c 	movl   $0x6c757365,0x8(%eax)
 8049c38:	c7 40 0c 74 20 73 74 	movl   $0x74732074,0xc(%eax)
 8049c3f:	c7 40 10 72 69 6e 67 	movl   $0x676e6972,0x10(%eax)
 8049c46:	c7 40 14 20 74 6f 6f 	movl   $0x6f6f7420,0x14(%eax)
 8049c4d:	c7 40 18 20 6c 61 72 	movl   $0x72616c20,0x18(%eax)
 8049c54:	c7 40 1c 67 65 2e 20 	movl   $0x202e6567,0x1c(%eax)
 8049c5b:	c7 40 20 49 6e 63 72 	movl   $0x72636e49,0x20(%eax)
 8049c62:	c7 40 24 65 61 73 65 	movl   $0x65736165,0x24(%eax)
 8049c69:	c7 40 28 20 53 55 42 	movl   $0x42555320,0x28(%eax)
 8049c70:	c7 40 2c 4d 49 54 52 	movl   $0x5254494d,0x2c(%eax)
 8049c77:	c7 40 30 5f 4d 41 58 	movl   $0x58414d5f,0x30(%eax)
 8049c7e:	c7 40 34 42 55 46 00 	movl   $0x465542,0x34(%eax)
 8049c85:	83 ec 0c             	sub    $0xc,%esp
 8049c88:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049c8e:	e8 fd ed ff ff       	call   8048a90 <close@plt>
 8049c93:	83 c4 10             	add    $0x10,%esp
 8049c96:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049c9b:	e9 31 04 00 00       	jmp    804a0d1 <submitr+0x705>
 8049ca0:	83 ec 08             	sub    $0x8,%esp
 8049ca3:	68 00 20 00 00       	push   $0x2000
 8049ca8:	8d 85 e4 9f ff ff    	lea    -0x601c(%ebp),%eax
 8049cae:	50                   	push   %eax
 8049caf:	e8 fc eb ff ff       	call   80488b0 <bzero@plt>
 8049cb4:	83 c4 10             	add    $0x10,%esp
 8049cb7:	83 ec 08             	sub    $0x8,%esp
 8049cba:	8d 85 e4 9f ff ff    	lea    -0x601c(%ebp),%eax
 8049cc0:	50                   	push   %eax
 8049cc1:	ff b5 94 5f ff ff    	pushl  -0xa06c(%ebp)
 8049cc7:	e8 9c fb ff ff       	call   8049868 <urlencode>
 8049ccc:	83 c4 10             	add    $0x10,%esp
 8049ccf:	85 c0                	test   %eax,%eax
 8049cd1:	79 51                	jns    8049d24 <submitr+0x358>
 8049cd3:	8b 85 90 5f ff ff    	mov    -0xa070(%ebp),%eax
 8049cd9:	bb 9c aa 04 08       	mov    $0x804aa9c,%ebx
 8049cde:	ba 43 00 00 00       	mov    $0x43,%edx
 8049ce3:	8b 0b                	mov    (%ebx),%ecx
 8049ce5:	89 08                	mov    %ecx,(%eax)
 8049ce7:	8b 4c 13 fc          	mov    -0x4(%ebx,%edx,1),%ecx
 8049ceb:	89 4c 10 fc          	mov    %ecx,-0x4(%eax,%edx,1)
 8049cef:	8d 78 04             	lea    0x4(%eax),%edi
 8049cf2:	83 e7 fc             	and    $0xfffffffc,%edi
 8049cf5:	29 f8                	sub    %edi,%eax
 8049cf7:	29 c3                	sub    %eax,%ebx
 8049cf9:	01 c2                	add    %eax,%edx
 8049cfb:	83 e2 fc             	and    $0xfffffffc,%edx
 8049cfe:	89 d0                	mov    %edx,%eax
 8049d00:	c1 e8 02             	shr    $0x2,%eax
 8049d03:	89 de                	mov    %ebx,%esi
 8049d05:	89 c1                	mov    %eax,%ecx
 8049d07:	f3 a5                	rep movsl %ds:(%esi),%es:(%edi)
 8049d09:	83 ec 0c             	sub    $0xc,%esp
 8049d0c:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049d12:	e8 79 ed ff ff       	call   8048a90 <close@plt>
 8049d17:	83 c4 10             	add    $0x10,%esp
 8049d1a:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049d1f:	e9 ad 03 00 00       	jmp    804a0d1 <submitr+0x705>
 8049d24:	83 ec 08             	sub    $0x8,%esp
 8049d27:	8d 85 e4 9f ff ff    	lea    -0x601c(%ebp),%eax
 8049d2d:	50                   	push   %eax
 8049d2e:	ff b5 98 5f ff ff    	pushl  -0xa068(%ebp)
 8049d34:	ff b5 9c 5f ff ff    	pushl  -0xa064(%ebp)
 8049d3a:	ff b5 a0 5f ff ff    	pushl  -0xa060(%ebp)
 8049d40:	68 e0 aa 04 08       	push   $0x804aae0
 8049d45:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 8049d4b:	50                   	push   %eax
 8049d4c:	e8 ef ec ff ff       	call   8048a40 <sprintf@plt>
 8049d51:	83 c4 20             	add    $0x20,%esp
 8049d54:	83 ec 0c             	sub    $0xc,%esp
 8049d57:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 8049d5d:	50                   	push   %eax
 8049d5e:	e8 3d ec ff ff       	call   80489a0 <strlen@plt>
 8049d63:	83 c4 10             	add    $0x10,%esp
 8049d66:	83 ec 04             	sub    $0x4,%esp
 8049d69:	50                   	push   %eax
 8049d6a:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 8049d70:	50                   	push   %eax
 8049d71:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049d77:	e8 88 fa ff ff       	call   8049804 <rio_writen>
 8049d7c:	83 c4 10             	add    $0x10,%esp
 8049d7f:	85 c0                	test   %eax,%eax
 8049d81:	79 6d                	jns    8049df0 <submitr+0x424>
 8049d83:	8b 85 90 5f ff ff    	mov    -0xa070(%ebp),%eax
 8049d89:	c7 00 45 72 72 6f    	movl   $0x6f727245,(%eax)
 8049d8f:	c7 40 04 72 3a 20 43 	movl   $0x43203a72,0x4(%eax)
 8049d96:	c7 40 08 6c 69 65 6e 	movl   $0x6e65696c,0x8(%eax)
 8049d9d:	c7 40 0c 74 20 75 6e 	movl   $0x6e752074,0xc(%eax)
 8049da4:	c7 40 10 61 62 6c 65 	movl   $0x656c6261,0x10(%eax)
 8049dab:	c7 40 14 20 74 6f 20 	movl   $0x206f7420,0x14(%eax)
 8049db2:	c7 40 18 77 72 69 74 	movl   $0x74697277,0x18(%eax)
 8049db9:	c7 40 1c 65 20 74 6f 	movl   $0x6f742065,0x1c(%eax)
 8049dc0:	c7 40 20 20 74 68 65 	movl   $0x65687420,0x20(%eax)
 8049dc7:	c7 40 24 20 73 65 72 	movl   $0x72657320,0x24(%eax)
 8049dce:	c7 40 28 76 65 72 00 	movl   $0x726576,0x28(%eax)
 8049dd5:	83 ec 0c             	sub    $0xc,%esp
 8049dd8:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049dde:	e8 ad ec ff ff       	call   8048a90 <close@plt>
 8049de3:	83 c4 10             	add    $0x10,%esp
 8049de6:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049deb:	e9 e1 02 00 00       	jmp    804a0d1 <submitr+0x705>
 8049df0:	83 ec 08             	sub    $0x8,%esp
 8049df3:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049df9:	8d 85 d8 5f ff ff    	lea    -0xa028(%ebp),%eax
 8049dff:	50                   	push   %eax
 8049e00:	e8 69 f8 ff ff       	call   804966e <rio_readinitb>
 8049e05:	83 c4 10             	add    $0x10,%esp
 8049e08:	83 ec 04             	sub    $0x4,%esp
 8049e0b:	68 00 20 00 00       	push   $0x2000
 8049e10:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 8049e16:	50                   	push   %eax
 8049e17:	8d 85 d8 5f ff ff    	lea    -0xa028(%ebp),%eax
 8049e1d:	50                   	push   %eax
 8049e1e:	e8 3a f9 ff ff       	call   804975d <rio_readlineb>
 8049e23:	83 c4 10             	add    $0x10,%esp
 8049e26:	85 c0                	test   %eax,%eax
 8049e28:	0f 8f 81 00 00 00    	jg     8049eaf <submitr+0x4e3>
 8049e2e:	8b 85 90 5f ff ff    	mov    -0xa070(%ebp),%eax
 8049e34:	c7 00 45 72 72 6f    	movl   $0x6f727245,(%eax)
 8049e3a:	c7 40 04 72 3a 20 43 	movl   $0x43203a72,0x4(%eax)
 8049e41:	c7 40 08 6c 69 65 6e 	movl   $0x6e65696c,0x8(%eax)
 8049e48:	c7 40 0c 74 20 75 6e 	movl   $0x6e752074,0xc(%eax)
 8049e4f:	c7 40 10 61 62 6c 65 	movl   $0x656c6261,0x10(%eax)
 8049e56:	c7 40 14 20 74 6f 20 	movl   $0x206f7420,0x14(%eax)
 8049e5d:	c7 40 18 72 65 61 64 	movl   $0x64616572,0x18(%eax)
 8049e64:	c7 40 1c 20 66 69 72 	movl   $0x72696620,0x1c(%eax)
 8049e6b:	c7 40 20 73 74 20 68 	movl   $0x68207473,0x20(%eax)
 8049e72:	c7 40 24 65 61 64 65 	movl   $0x65646165,0x24(%eax)
 8049e79:	c7 40 28 72 20 66 72 	movl   $0x72662072,0x28(%eax)
 8049e80:	c7 40 2c 6f 6d 20 73 	movl   $0x73206d6f,0x2c(%eax)
 8049e87:	c7 40 30 65 72 76 65 	movl   $0x65767265,0x30(%eax)
 8049e8e:	66 c7 40 34 72 00    	movw   $0x72,0x34(%eax)
 8049e94:	83 ec 0c             	sub    $0xc,%esp
 8049e97:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049e9d:	e8 ee eb ff ff       	call   8048a90 <close@plt>
 8049ea2:	83 c4 10             	add    $0x10,%esp
 8049ea5:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049eaa:	e9 22 02 00 00       	jmp    804a0d1 <submitr+0x705>
 8049eaf:	83 ec 0c             	sub    $0xc,%esp
 8049eb2:	8d 85 e4 df ff ff    	lea    -0x201c(%ebp),%eax
 8049eb8:	50                   	push   %eax
 8049eb9:	8d 85 b4 5f ff ff    	lea    -0xa04c(%ebp),%eax
 8049ebf:	50                   	push   %eax
 8049ec0:	8d 85 e4 bf ff ff    	lea    -0x401c(%ebp),%eax
 8049ec6:	50                   	push   %eax
 8049ec7:	68 2a ab 04 08       	push   $0x804ab2a
 8049ecc:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 8049ed2:	50                   	push   %eax
 8049ed3:	e8 18 eb ff ff       	call   80489f0 <__isoc99_sscanf@plt>
 8049ed8:	83 c4 20             	add    $0x20,%esp
 8049edb:	8b 85 b4 5f ff ff    	mov    -0xa04c(%ebp),%eax
 8049ee1:	3d c8 00 00 00       	cmp    $0xc8,%eax
 8049ee6:	0f 84 d6 00 00 00    	je     8049fc2 <submitr+0x5f6>
 8049eec:	8b 85 b4 5f ff ff    	mov    -0xa04c(%ebp),%eax
 8049ef2:	8d 95 e4 df ff ff    	lea    -0x201c(%ebp),%edx
 8049ef8:	52                   	push   %edx
 8049ef9:	50                   	push   %eax
 8049efa:	68 3c ab 04 08       	push   $0x804ab3c
 8049eff:	ff b5 90 5f ff ff    	pushl  -0xa070(%ebp)
 8049f05:	e8 36 eb ff ff       	call   8048a40 <sprintf@plt>
 8049f0a:	83 c4 10             	add    $0x10,%esp
 8049f0d:	83 ec 0c             	sub    $0xc,%esp
 8049f10:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049f16:	e8 75 eb ff ff       	call   8048a90 <close@plt>
 8049f1b:	83 c4 10             	add    $0x10,%esp
 8049f1e:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049f23:	e9 a9 01 00 00       	jmp    804a0d1 <submitr+0x705>
 8049f28:	83 ec 04             	sub    $0x4,%esp
 8049f2b:	68 00 20 00 00       	push   $0x2000
 8049f30:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 8049f36:	50                   	push   %eax
 8049f37:	8d 85 d8 5f ff ff    	lea    -0xa028(%ebp),%eax
 8049f3d:	50                   	push   %eax
 8049f3e:	e8 1a f8 ff ff       	call   804975d <rio_readlineb>
 8049f43:	83 c4 10             	add    $0x10,%esp
 8049f46:	85 c0                	test   %eax,%eax
 8049f48:	7f 78                	jg     8049fc2 <submitr+0x5f6>
 8049f4a:	8b 85 90 5f ff ff    	mov    -0xa070(%ebp),%eax
 8049f50:	c7 00 45 72 72 6f    	movl   $0x6f727245,(%eax)
 8049f56:	c7 40 04 72 3a 20 43 	movl   $0x43203a72,0x4(%eax)
 8049f5d:	c7 40 08 6c 69 65 6e 	movl   $0x6e65696c,0x8(%eax)
 8049f64:	c7 40 0c 74 20 75 6e 	movl   $0x6e752074,0xc(%eax)
 8049f6b:	c7 40 10 61 62 6c 65 	movl   $0x656c6261,0x10(%eax)
 8049f72:	c7 40 14 20 74 6f 20 	movl   $0x206f7420,0x14(%eax)
 8049f79:	c7 40 18 72 65 61 64 	movl   $0x64616572,0x18(%eax)
 8049f80:	c7 40 1c 20 68 65 61 	movl   $0x61656820,0x1c(%eax)
 8049f87:	c7 40 20 64 65 72 73 	movl   $0x73726564,0x20(%eax)
 8049f8e:	c7 40 24 20 66 72 6f 	movl   $0x6f726620,0x24(%eax)
 8049f95:	c7 40 28 6d 20 73 65 	movl   $0x6573206d,0x28(%eax)
 8049f9c:	c7 40 2c 72 76 65 72 	movl   $0x72657672,0x2c(%eax)
 8049fa3:	c6 40 30 00          	movb   $0x0,0x30(%eax)
 8049fa7:	83 ec 0c             	sub    $0xc,%esp
 8049faa:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 8049fb0:	e8 db ea ff ff       	call   8048a90 <close@plt>
 8049fb5:	83 c4 10             	add    $0x10,%esp
 8049fb8:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 8049fbd:	e9 0f 01 00 00       	jmp    804a0d1 <submitr+0x705>
 8049fc2:	83 ec 08             	sub    $0x8,%esp
 8049fc5:	68 69 ab 04 08       	push   $0x804ab69
 8049fca:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 8049fd0:	50                   	push   %eax
 8049fd1:	e8 7a e8 ff ff       	call   8048850 <strcmp@plt>
 8049fd6:	83 c4 10             	add    $0x10,%esp
 8049fd9:	85 c0                	test   %eax,%eax
 8049fdb:	0f 85 47 ff ff ff    	jne    8049f28 <submitr+0x55c>
 8049fe1:	83 ec 04             	sub    $0x4,%esp
 8049fe4:	68 00 20 00 00       	push   $0x2000
 8049fe9:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 8049fef:	50                   	push   %eax
 8049ff0:	8d 85 d8 5f ff ff    	lea    -0xa028(%ebp),%eax
 8049ff6:	50                   	push   %eax
 8049ff7:	e8 61 f7 ff ff       	call   804975d <rio_readlineb>
 8049ffc:	83 c4 10             	add    $0x10,%esp
 8049fff:	85 c0                	test   %eax,%eax
 804a001:	7f 7f                	jg     804a082 <submitr+0x6b6>
 804a003:	8b 85 90 5f ff ff    	mov    -0xa070(%ebp),%eax
 804a009:	c7 00 45 72 72 6f    	movl   $0x6f727245,(%eax)
 804a00f:	c7 40 04 72 3a 20 43 	movl   $0x43203a72,0x4(%eax)
 804a016:	c7 40 08 6c 69 65 6e 	movl   $0x6e65696c,0x8(%eax)
 804a01d:	c7 40 0c 74 20 75 6e 	movl   $0x6e752074,0xc(%eax)
 804a024:	c7 40 10 61 62 6c 65 	movl   $0x656c6261,0x10(%eax)
 804a02b:	c7 40 14 20 74 6f 20 	movl   $0x206f7420,0x14(%eax)
 804a032:	c7 40 18 72 65 61 64 	movl   $0x64616572,0x18(%eax)
 804a039:	c7 40 1c 20 73 74 61 	movl   $0x61747320,0x1c(%eax)
 804a040:	c7 40 20 74 75 73 20 	movl   $0x20737574,0x20(%eax)
 804a047:	c7 40 24 6d 65 73 73 	movl   $0x7373656d,0x24(%eax)
 804a04e:	c7 40 28 61 67 65 20 	movl   $0x20656761,0x28(%eax)
 804a055:	c7 40 2c 66 72 6f 6d 	movl   $0x6d6f7266,0x2c(%eax)
 804a05c:	c7 40 30 20 73 65 72 	movl   $0x72657320,0x30(%eax)
 804a063:	c7 40 34 76 65 72 00 	movl   $0x726576,0x34(%eax)
 804a06a:	83 ec 0c             	sub    $0xc,%esp
 804a06d:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 804a073:	e8 18 ea ff ff       	call   8048a90 <close@plt>
 804a078:	83 c4 10             	add    $0x10,%esp
 804a07b:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 804a080:	eb 4f                	jmp    804a0d1 <submitr+0x705>
 804a082:	83 ec 08             	sub    $0x8,%esp
 804a085:	8d 85 e4 7f ff ff    	lea    -0x801c(%ebp),%eax
 804a08b:	50                   	push   %eax
 804a08c:	ff b5 90 5f ff ff    	pushl  -0xa070(%ebp)
 804a092:	e8 99 e8 ff ff       	call   8048930 <strcpy@plt>
 804a097:	83 c4 10             	add    $0x10,%esp
 804a09a:	83 ec 0c             	sub    $0xc,%esp
 804a09d:	ff b5 b8 5f ff ff    	pushl  -0xa048(%ebp)
 804a0a3:	e8 e8 e9 ff ff       	call   8048a90 <close@plt>
 804a0a8:	83 c4 10             	add    $0x10,%esp
 804a0ab:	83 ec 08             	sub    $0x8,%esp
 804a0ae:	68 6c ab 04 08       	push   $0x804ab6c
 804a0b3:	ff b5 90 5f ff ff    	pushl  -0xa070(%ebp)
 804a0b9:	e8 92 e7 ff ff       	call   8048850 <strcmp@plt>
 804a0be:	83 c4 10             	add    $0x10,%esp
 804a0c1:	85 c0                	test   %eax,%eax
 804a0c3:	75 07                	jne    804a0cc <submitr+0x700>
 804a0c5:	b8 00 00 00 00       	mov    $0x0,%eax
 804a0ca:	eb 05                	jmp    804a0d1 <submitr+0x705>
 804a0cc:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 804a0d1:	8b 75 e4             	mov    -0x1c(%ebp),%esi
 804a0d4:	65 33 35 14 00 00 00 	xor    %gs:0x14,%esi
 804a0db:	74 05                	je     804a0e2 <submitr+0x716>
 804a0dd:	e8 fe e7 ff ff       	call   80488e0 <__stack_chk_fail@plt>
 804a0e2:	8d 65 f4             	lea    -0xc(%ebp),%esp
 804a0e5:	5b                   	pop    %ebx
 804a0e6:	5e                   	pop    %esi
 804a0e7:	5f                   	pop    %edi
 804a0e8:	5d                   	pop    %ebp
 804a0e9:	c3                   	ret    

0804a0ea <init_timeout>:
 804a0ea:	55                   	push   %ebp
 804a0eb:	89 e5                	mov    %esp,%ebp
 804a0ed:	83 ec 08             	sub    $0x8,%esp
 804a0f0:	83 7d 08 00          	cmpl   $0x0,0x8(%ebp)
 804a0f4:	74 30                	je     804a126 <init_timeout+0x3c>
 804a0f6:	83 7d 08 00          	cmpl   $0x0,0x8(%ebp)
 804a0fa:	79 07                	jns    804a103 <init_timeout+0x19>
 804a0fc:	c7 45 08 02 00 00 00 	movl   $0x2,0x8(%ebp)
 804a103:	83 ec 08             	sub    $0x8,%esp
 804a106:	68 4c 96 04 08       	push   $0x804964c
 804a10b:	6a 0e                	push   $0xe
 804a10d:	e8 ae e7 ff ff       	call   80488c0 <signal@plt>
 804a112:	83 c4 10             	add    $0x10,%esp
 804a115:	8b 45 08             	mov    0x8(%ebp),%eax
 804a118:	83 ec 0c             	sub    $0xc,%esp
 804a11b:	50                   	push   %eax
 804a11c:	e8 af e7 ff ff       	call   80488d0 <alarm@plt>
 804a121:	83 c4 10             	add    $0x10,%esp
 804a124:	eb 01                	jmp    804a127 <init_timeout+0x3d>
 804a126:	90                   	nop
 804a127:	c9                   	leave  
 804a128:	c3                   	ret    

0804a129 <init_driver>:
 804a129:	55                   	push   %ebp
 804a12a:	89 e5                	mov    %esp,%ebp
 804a12c:	83 ec 48             	sub    $0x48,%esp
 804a12f:	8b 45 08             	mov    0x8(%ebp),%eax
 804a132:	89 45 c4             	mov    %eax,-0x3c(%ebp)
 804a135:	65 a1 14 00 00 00    	mov    %gs:0x14,%eax
 804a13b:	89 45 f4             	mov    %eax,-0xc(%ebp)
 804a13e:	31 c0                	xor    %eax,%eax
 804a140:	c7 45 d4 6f ab 04 08 	movl   $0x804ab6f,-0x2c(%ebp)
 804a147:	c7 45 d8 26 47 00 00 	movl   $0x4726,-0x28(%ebp)
 804a14e:	83 ec 08             	sub    $0x8,%esp
 804a151:	6a 01                	push   $0x1
 804a153:	6a 0d                	push   $0xd
 804a155:	e8 66 e7 ff ff       	call   80488c0 <signal@plt>
 804a15a:	83 c4 10             	add    $0x10,%esp
 804a15d:	83 ec 08             	sub    $0x8,%esp
 804a160:	6a 01                	push   $0x1
 804a162:	6a 1d                	push   $0x1d
 804a164:	e8 57 e7 ff ff       	call   80488c0 <signal@plt>
 804a169:	83 c4 10             	add    $0x10,%esp
 804a16c:	83 ec 08             	sub    $0x8,%esp
 804a16f:	6a 01                	push   $0x1
 804a171:	6a 1d                	push   $0x1d
 804a173:	e8 48 e7 ff ff       	call   80488c0 <signal@plt>
 804a178:	83 c4 10             	add    $0x10,%esp
 804a17b:	83 ec 04             	sub    $0x4,%esp
 804a17e:	6a 00                	push   $0x0
 804a180:	6a 01                	push   $0x1
 804a182:	6a 02                	push   $0x2
 804a184:	e8 c7 e8 ff ff       	call   8048a50 <socket@plt>
 804a189:	83 c4 10             	add    $0x10,%esp
 804a18c:	89 45 dc             	mov    %eax,-0x24(%ebp)
 804a18f:	83 7d dc 00          	cmpl   $0x0,-0x24(%ebp)
 804a193:	79 51                	jns    804a1e6 <init_driver+0xbd>
 804a195:	8b 45 c4             	mov    -0x3c(%ebp),%eax
 804a198:	c7 00 45 72 72 6f    	movl   $0x6f727245,(%eax)
 804a19e:	c7 40 04 72 3a 20 43 	movl   $0x43203a72,0x4(%eax)
 804a1a5:	c7 40 08 6c 69 65 6e 	movl   $0x6e65696c,0x8(%eax)
 804a1ac:	c7 40 0c 74 20 75 6e 	movl   $0x6e752074,0xc(%eax)
 804a1b3:	c7 40 10 61 62 6c 65 	movl   $0x656c6261,0x10(%eax)
 804a1ba:	c7 40 14 20 74 6f 20 	movl   $0x206f7420,0x14(%eax)
 804a1c1:	c7 40 18 63 72 65 61 	movl   $0x61657263,0x18(%eax)
 804a1c8:	c7 40 1c 74 65 20 73 	movl   $0x73206574,0x1c(%eax)
 804a1cf:	c7 40 20 6f 63 6b 65 	movl   $0x656b636f,0x20(%eax)
 804a1d6:	66 c7 40 24 74 00    	movw   $0x74,0x24(%eax)
 804a1dc:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 804a1e1:	e9 f8 00 00 00       	jmp    804a2de <init_driver+0x1b5>
 804a1e6:	83 ec 0c             	sub    $0xc,%esp
 804a1e9:	ff 75 d4             	pushl  -0x2c(%ebp)
 804a1ec:	e8 7f e8 ff ff       	call   8048a70 <gethostbyname@plt>
 804a1f1:	83 c4 10             	add    $0x10,%esp
 804a1f4:	89 45 e0             	mov    %eax,-0x20(%ebp)
 804a1f7:	83 7d e0 00          	cmpl   $0x0,-0x20(%ebp)
 804a1fb:	75 2e                	jne    804a22b <init_driver+0x102>
 804a1fd:	83 ec 04             	sub    $0x4,%esp
 804a200:	ff 75 d4             	pushl  -0x2c(%ebp)
 804a203:	68 48 aa 04 08       	push   $0x804aa48
 804a208:	ff 75 c4             	pushl  -0x3c(%ebp)
 804a20b:	e8 30 e8 ff ff       	call   8048a40 <sprintf@plt>
 804a210:	83 c4 10             	add    $0x10,%esp
 804a213:	83 ec 0c             	sub    $0xc,%esp
 804a216:	ff 75 dc             	pushl  -0x24(%ebp)
 804a219:	e8 72 e8 ff ff       	call   8048a90 <close@plt>
 804a21e:	83 c4 10             	add    $0x10,%esp
 804a221:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 804a226:	e9 b3 00 00 00       	jmp    804a2de <init_driver+0x1b5>
 804a22b:	83 ec 08             	sub    $0x8,%esp
 804a22e:	6a 10                	push   $0x10
 804a230:	8d 45 e4             	lea    -0x1c(%ebp),%eax
 804a233:	50                   	push   %eax
 804a234:	e8 77 e6 ff ff       	call   80488b0 <bzero@plt>
 804a239:	83 c4 10             	add    $0x10,%esp
 804a23c:	66 c7 45 e4 02 00    	movw   $0x2,-0x1c(%ebp)
 804a242:	8b 45 e0             	mov    -0x20(%ebp),%eax
 804a245:	8b 40 0c             	mov    0xc(%eax),%eax
 804a248:	89 c2                	mov    %eax,%edx
 804a24a:	8b 45 e0             	mov    -0x20(%ebp),%eax
 804a24d:	8b 40 10             	mov    0x10(%eax),%eax
 804a250:	8b 00                	mov    (%eax),%eax
 804a252:	83 ec 04             	sub    $0x4,%esp
 804a255:	52                   	push   %edx
 804a256:	8d 55 e4             	lea    -0x1c(%ebp),%edx
 804a259:	83 c2 04             	add    $0x4,%edx
 804a25c:	52                   	push   %edx
 804a25d:	50                   	push   %eax
 804a25e:	e8 bd e6 ff ff       	call   8048920 <bcopy@plt>
 804a263:	83 c4 10             	add    $0x10,%esp
 804a266:	8b 45 d8             	mov    -0x28(%ebp),%eax
 804a269:	0f b7 c0             	movzwl %ax,%eax
 804a26c:	83 ec 0c             	sub    $0xc,%esp
 804a26f:	50                   	push   %eax
 804a270:	e8 8b e6 ff ff       	call   8048900 <htons@plt>
 804a275:	83 c4 10             	add    $0x10,%esp
 804a278:	66 89 45 e6          	mov    %ax,-0x1a(%ebp)
 804a27c:	83 ec 04             	sub    $0x4,%esp
 804a27f:	6a 10                	push   $0x10
 804a281:	8d 45 e4             	lea    -0x1c(%ebp),%eax
 804a284:	50                   	push   %eax
 804a285:	ff 75 dc             	pushl  -0x24(%ebp)
 804a288:	e8 f3 e7 ff ff       	call   8048a80 <connect@plt>
 804a28d:	83 c4 10             	add    $0x10,%esp
 804a290:	85 c0                	test   %eax,%eax
 804a292:	79 2b                	jns    804a2bf <init_driver+0x196>
 804a294:	ff 75 d8             	pushl  -0x28(%ebp)
 804a297:	ff 75 d4             	pushl  -0x2c(%ebp)
 804a29a:	68 7c ab 04 08       	push   $0x804ab7c
 804a29f:	ff 75 c4             	pushl  -0x3c(%ebp)
 804a2a2:	e8 99 e7 ff ff       	call   8048a40 <sprintf@plt>
 804a2a7:	83 c4 10             	add    $0x10,%esp
 804a2aa:	83 ec 0c             	sub    $0xc,%esp
 804a2ad:	ff 75 dc             	pushl  -0x24(%ebp)
 804a2b0:	e8 db e7 ff ff       	call   8048a90 <close@plt>
 804a2b5:	83 c4 10             	add    $0x10,%esp
 804a2b8:	b8 ff ff ff ff       	mov    $0xffffffff,%eax
 804a2bd:	eb 1f                	jmp    804a2de <init_driver+0x1b5>
 804a2bf:	83 ec 0c             	sub    $0xc,%esp
 804a2c2:	ff 75 dc             	pushl  -0x24(%ebp)
 804a2c5:	e8 c6 e7 ff ff       	call   8048a90 <close@plt>
 804a2ca:	83 c4 10             	add    $0x10,%esp
 804a2cd:	8b 45 c4             	mov    -0x3c(%ebp),%eax
 804a2d0:	66 c7 00 4f 4b       	movw   $0x4b4f,(%eax)
 804a2d5:	c6 40 02 00          	movb   $0x0,0x2(%eax)
 804a2d9:	b8 00 00 00 00       	mov    $0x0,%eax
 804a2de:	8b 4d f4             	mov    -0xc(%ebp),%ecx
 804a2e1:	65 33 0d 14 00 00 00 	xor    %gs:0x14,%ecx
 804a2e8:	74 05                	je     804a2ef <init_driver+0x1c6>
 804a2ea:	e8 f1 e5 ff ff       	call   80488e0 <__stack_chk_fail@plt>
 804a2ef:	c9                   	leave  
 804a2f0:	c3                   	ret    

0804a2f1 <driver_post>:
 804a2f1:	55                   	push   %ebp
 804a2f2:	89 e5                	mov    %esp,%ebp
 804a2f4:	83 ec 18             	sub    $0x18,%esp
 804a2f7:	83 7d 10 00          	cmpl   $0x0,0x10(%ebp)
 804a2fb:	74 26                	je     804a323 <driver_post+0x32>
 804a2fd:	83 ec 08             	sub    $0x8,%esp
 804a300:	ff 75 0c             	pushl  0xc(%ebp)
 804a303:	68 a5 ab 04 08       	push   $0x804aba5
 804a308:	e8 73 e5 ff ff       	call   8048880 <printf@plt>
 804a30d:	83 c4 10             	add    $0x10,%esp
 804a310:	8b 45 14             	mov    0x14(%ebp),%eax
 804a313:	66 c7 00 4f 4b       	movw   $0x4b4f,(%eax)
 804a318:	c6 40 02 00          	movb   $0x0,0x2(%eax)
 804a31c:	b8 00 00 00 00       	mov    $0x0,%eax
 804a321:	eb 51                	jmp    804a374 <driver_post+0x83>
 804a323:	83 7d 08 00          	cmpl   $0x0,0x8(%ebp)
 804a327:	74 3a                	je     804a363 <driver_post+0x72>
 804a329:	8b 45 08             	mov    0x8(%ebp),%eax
 804a32c:	0f b6 00             	movzbl (%eax),%eax
 804a32f:	84 c0                	test   %al,%al
 804a331:	74 30                	je     804a363 <driver_post+0x72>
 804a333:	83 ec 04             	sub    $0x4,%esp
 804a336:	ff 75 14             	pushl  0x14(%ebp)
 804a339:	ff 75 0c             	pushl  0xc(%ebp)
 804a33c:	68 bc ab 04 08       	push   $0x804abbc
 804a341:	ff 75 08             	pushl  0x8(%ebp)
 804a344:	68 c3 ab 04 08       	push   $0x804abc3
 804a349:	68 26 47 00 00       	push   $0x4726
 804a34e:	68 6f ab 04 08       	push   $0x804ab6f
 804a353:	e8 74 f6 ff ff       	call   80499cc <submitr>
 804a358:	83 c4 20             	add    $0x20,%esp
 804a35b:	89 45 f4             	mov    %eax,-0xc(%ebp)
 804a35e:	8b 45 f4             	mov    -0xc(%ebp),%eax
 804a361:	eb 11                	jmp    804a374 <driver_post+0x83>
 804a363:	8b 45 14             	mov    0x14(%ebp),%eax
 804a366:	66 c7 00 4f 4b       	movw   $0x4b4f,(%eax)
 804a36b:	c6 40 02 00          	movb   $0x0,0x2(%eax)
 804a36f:	b8 00 00 00 00       	mov    $0x0,%eax
 804a374:	c9                   	leave  
 804a375:	c3                   	ret    

0804a376 <hash>:
 804a376:	55                   	push   %ebp
 804a377:	89 e5                	mov    %esp,%ebp
 804a379:	83 ec 10             	sub    $0x10,%esp
 804a37c:	c7 45 fc 00 00 00 00 	movl   $0x0,-0x4(%ebp)
 804a383:	eb 1a                	jmp    804a39f <hash+0x29>
 804a385:	8b 45 fc             	mov    -0x4(%ebp),%eax
 804a388:	6b c8 67             	imul   $0x67,%eax,%ecx
 804a38b:	8b 45 08             	mov    0x8(%ebp),%eax
 804a38e:	8d 50 01             	lea    0x1(%eax),%edx
 804a391:	89 55 08             	mov    %edx,0x8(%ebp)
 804a394:	0f b6 00             	movzbl (%eax),%eax
 804a397:	0f be c0             	movsbl %al,%eax
 804a39a:	01 c8                	add    %ecx,%eax
 804a39c:	89 45 fc             	mov    %eax,-0x4(%ebp)
 804a39f:	8b 45 08             	mov    0x8(%ebp),%eax
 804a3a2:	0f b6 00             	movzbl (%eax),%eax
 804a3a5:	84 c0                	test   %al,%al
 804a3a7:	75 dc                	jne    804a385 <hash+0xf>
 804a3a9:	8b 45 fc             	mov    -0x4(%ebp),%eax
 804a3ac:	c9                   	leave  
 804a3ad:	c3                   	ret    

0804a3ae <check>:
 804a3ae:	55                   	push   %ebp
 804a3af:	89 e5                	mov    %esp,%ebp
 804a3b1:	83 ec 10             	sub    $0x10,%esp
 804a3b4:	8b 45 08             	mov    0x8(%ebp),%eax
 804a3b7:	c1 e8 1c             	shr    $0x1c,%eax
 804a3ba:	85 c0                	test   %eax,%eax
 804a3bc:	75 07                	jne    804a3c5 <check+0x17>
 804a3be:	b8 00 00 00 00       	mov    $0x0,%eax
 804a3c3:	eb 33                	jmp    804a3f8 <check+0x4a>
 804a3c5:	c7 45 fc 00 00 00 00 	movl   $0x0,-0x4(%ebp)
 804a3cc:	eb 1f                	jmp    804a3ed <check+0x3f>
 804a3ce:	8b 45 fc             	mov    -0x4(%ebp),%eax
 804a3d1:	8b 55 08             	mov    0x8(%ebp),%edx
 804a3d4:	89 c1                	mov    %eax,%ecx
 804a3d6:	d3 ea                	shr    %cl,%edx
 804a3d8:	89 d0                	mov    %edx,%eax
 804a3da:	0f b6 c0             	movzbl %al,%eax
 804a3dd:	83 f8 0a             	cmp    $0xa,%eax
 804a3e0:	75 07                	jne    804a3e9 <check+0x3b>
 804a3e2:	b8 00 00 00 00       	mov    $0x0,%eax
 804a3e7:	eb 0f                	jmp    804a3f8 <check+0x4a>
 804a3e9:	83 45 fc 08          	addl   $0x8,-0x4(%ebp)
 804a3ed:	83 7d fc 1f          	cmpl   $0x1f,-0x4(%ebp)
 804a3f1:	7e db                	jle    804a3ce <check+0x20>
 804a3f3:	b8 01 00 00 00       	mov    $0x1,%eax
 804a3f8:	c9                   	leave  
 804a3f9:	c3                   	ret    

0804a3fa <gencookie>:
 804a3fa:	55                   	push   %ebp
 804a3fb:	89 e5                	mov    %esp,%ebp
 804a3fd:	83 ec 18             	sub    $0x18,%esp
 804a400:	ff 75 08             	pushl  0x8(%ebp)
 804a403:	e8 6e ff ff ff       	call   804a376 <hash>
 804a408:	83 c4 04             	add    $0x4,%esp
 804a40b:	83 ec 0c             	sub    $0xc,%esp
 804a40e:	50                   	push   %eax
 804a40f:	e8 6c e5 ff ff       	call   8048980 <srand@plt>
 804a414:	83 c4 10             	add    $0x10,%esp
 804a417:	e8 04 e6 ff ff       	call   8048a20 <rand@plt>
 804a41c:	89 45 f4             	mov    %eax,-0xc(%ebp)
 804a41f:	83 ec 0c             	sub    $0xc,%esp
 804a422:	ff 75 f4             	pushl  -0xc(%ebp)
 804a425:	e8 84 ff ff ff       	call   804a3ae <check>
 804a42a:	83 c4 10             	add    $0x10,%esp
 804a42d:	85 c0                	test   %eax,%eax
 804a42f:	74 e6                	je     804a417 <gencookie+0x1d>
 804a431:	8b 45 f4             	mov    -0xc(%ebp),%eax
 804a434:	c9                   	leave  
 804a435:	c3                   	ret    
 804a436:	66 90                	xchg   %ax,%ax
 804a438:	66 90                	xchg   %ax,%ax
 804a43a:	66 90                	xchg   %ax,%ax
 804a43c:	66 90                	xchg   %ax,%ax
 804a43e:	66 90                	xchg   %ax,%ax

0804a440 <__libc_csu_init>:
 804a440:	55                   	push   %ebp
 804a441:	57                   	push   %edi
 804a442:	56                   	push   %esi
 804a443:	53                   	push   %ebx
 804a444:	e8 a7 e6 ff ff       	call   8048af0 <__x86.get_pc_thunk.bx>
 804a449:	81 c3 b7 2b 00 00    	add    $0x2bb7,%ebx
 804a44f:	83 ec 0c             	sub    $0xc,%esp
 804a452:	8b 6c 24 20          	mov    0x20(%esp),%ebp
 804a456:	8d b3 0c ff ff ff    	lea    -0xf4(%ebx),%esi
 804a45c:	e8 bb e3 ff ff       	call   804881c <_init>
 804a461:	8d 83 08 ff ff ff    	lea    -0xf8(%ebx),%eax
 804a467:	29 c6                	sub    %eax,%esi
 804a469:	c1 fe 02             	sar    $0x2,%esi
 804a46c:	85 f6                	test   %esi,%esi
 804a46e:	74 25                	je     804a495 <__libc_csu_init+0x55>
 804a470:	31 ff                	xor    %edi,%edi
 804a472:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
 804a478:	83 ec 04             	sub    $0x4,%esp
 804a47b:	ff 74 24 2c          	pushl  0x2c(%esp)
 804a47f:	ff 74 24 2c          	pushl  0x2c(%esp)
 804a483:	55                   	push   %ebp
 804a484:	ff 94 bb 08 ff ff ff 	call   *-0xf8(%ebx,%edi,4)
 804a48b:	83 c7 01             	add    $0x1,%edi
 804a48e:	83 c4 10             	add    $0x10,%esp
 804a491:	39 f7                	cmp    %esi,%edi
 804a493:	75 e3                	jne    804a478 <__libc_csu_init+0x38>
 804a495:	83 c4 0c             	add    $0xc,%esp
 804a498:	5b                   	pop    %ebx
 804a499:	5e                   	pop    %esi
 804a49a:	5f                   	pop    %edi
 804a49b:	5d                   	pop    %ebp
 804a49c:	c3                   	ret    
 804a49d:	8d 76 00             	lea    0x0(%esi),%esi

0804a4a0 <__libc_csu_fini>:
 804a4a0:	f3 c3                	repz ret 

Disassembly of section .fini:

0804a4a4 <_fini>:
 804a4a4:	53                   	push   %ebx
 804a4a5:	83 ec 08             	sub    $0x8,%esp
 804a4a8:	e8 43 e6 ff ff       	call   8048af0 <__x86.get_pc_thunk.bx>
 804a4ad:	81 c3 53 2b 00 00    	add    $0x2b53,%ebx
 804a4b3:	83 c4 08             	add    $0x8,%esp
 804a4b6:	5b                   	pop    %ebx
 804a4b7:	c3                   	ret    
