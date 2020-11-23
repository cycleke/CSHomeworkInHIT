	.file	"test.c"
	.text
	.globl	main
	.type	main, @function
main:
.LFB0:
	.cfi_startproc
  mov $0x08048ca7, %eax
  mov %eax, 0x55683864
  mov $0x7ca8d0c4, %eax
  mov $0x556833c0, %ebp
	jmp 0x08048ca7
	.cfi_endproc
.LFE0:
	.size	main, .-main
	.ident	"GCC: (GNU) 9.2.0"
	.section	.note.GNU-stack,"",@progbits
