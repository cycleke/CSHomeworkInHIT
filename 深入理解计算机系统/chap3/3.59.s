# dest in %rdi, x in %rsi, y in %rdx
store_prod:
  movq %rdx, %rax     # %rax = y
  cqto                # expand sign bit of %rax(y) to %rdx
  # %rdx = y_h

  movq %rsi, %rcx     # %rcx = x
  sarq $63, %rcx      # %rcx = x >> 63
  # %rcx = x_h

  imulq %rax, %rcx    # %rcx = x_h * y_l
  imulq %rsi, %rdx    # %rdx = y_h * x_l
  addq %rdx, %rcx     # %rcx = x_h * y_l + x_l * y_h
  mulq %rsi           # %rdx:%rax = x_l * y_l, %rax = (x_l * y_l)_l = p_l
  addq %rcx, %rdx     # %rdx = x_h * y_l + x_l * y_h + (x_l * y_l)_h = p_h

  movq %rax, (%rdi)
  movq %rdx, 8(%rdi)
  # *dest = p_h:p_l
  ret
