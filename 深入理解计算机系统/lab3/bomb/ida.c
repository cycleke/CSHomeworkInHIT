void __fastcall phase_2(char *input)
{
  signed int i; // ebx
  int numbers[6]; // [rsp+0h] [rbp-30h]

  read_six_numbers(input, numbers);
  if ( numbers[0] || numbers[1] != 1 )
    explode_bomb();
  for ( i = 2; i <= 5; ++i )
    {
      if ( numbers[i] != numbers[i - 2] + numbers[i - 1] )
        explode_bomb();
    }
}

void __fastcall phase_3(char *input)
{
  signed int v1; // eax
  int val; // [rsp+8h] [rbp-8h]
  int index; // [rsp+Ch] [rbp-4h]

  if ( (signed int)__isoc99_sscanf(input, "%d %d", &index, &val) <= 1 )
    explode_bomb();
  switch ( index )
    {
    case 0:
      v1 = 108;
      break;
    case 1:
      v1 = 169;
      break;
    case 2:
      v1 = 197;
      break;
    case 3:
      v1 = 290;
      break;
    case 4:
      v1 = 938;
      break;
    case 5:
      v1 = 56;
      break;
    case 6:
      v1 = 645;
      break;
    case 7:
      v1 = 192;
      break;
    default:
      explode_bomb();
      return;
    }
  if ( val != v1 )
    explode_bomb();
}

void __fastcall phase_4(char *input)
{
  int v1; // eax
  int val; // [rsp+8h] [rbp-8h]
  int base; // [rsp+Ch] [rbp-4h]

  if ( (unsigned int)__isoc99_sscanf(input, "%d %d", &val, &base) != 2 || base <= 1 || base > 4 )
    explode_bomb();
  v1 = func4(5, base);
  if ( val != v1 )
    explode_bomb();
}

int __fastcall func4(int n, int base)
{
  int v2; // er13

  if ( n <= 0 )
    return 0;
  if ( n == 1 )
    return base;
  v2 = func4(n - 1, base) + base;
  return v2 + func4(n - 2, base);
}

void __cdecl phase_defused()
{
  __int64 v0; // rbp
  int tmp2; // [rsp+0h] [rbp-60h]
  int tmp1; // [rsp+4h] [rbp-5Ch]
  char passphrase[80]; // [rsp+8h] [rbp-58h]
  __int64 v4; // [rsp+58h] [rbp-8h]

  if ( num_input_strings == 6 )
    {
      v4 = v0;
      if ( (unsigned int)__isoc99_sscanf(4216944LL, "%d %d %s", &tmp1, &tmp2) == 3
           && !strings_not_equal(passphrase, "DrEvil") )
        {
          puts("Curses, you've found the secret phase!");
          puts("But finding it and solving it are quite different...");
          secret_phase();
        }
      puts("Congratulations! You've defused the bomb!");
    }
}

void __cdecl secret_phase()
{
  char *v0; // rax
  int v1; // ebx

  v0 = read_line();
  v1 = strtol(v0, 0LL, 10);
  if ( (unsigned int)(v1 - 1) > 0x3E8 )
    explode_bomb();
  if ( fun7(&n1, v1) != 3 )
    explode_bomb();
  puts("Wow! You've defused the secret stage!");
  phase_defused();
}

int __fastcall fun7(treeNode *node, int val)
{
  if ( !node )
    return -1;
  if ( node->value > val )
    return 2 * fun7(node->left, val);
  if ( node->value == val )
    return 0;
  return 2 * fun7(node->right, val) + 1;
}

void __fastcall phase_5(char *input)
{
  int v1; // ecx
  signed int i; // eax

  if ( string_length(input) != 6 )
    explode_bomb();
  v1 = 0;
  for ( i = 0; i <= 5; ++i )
    v1 += array_3511[input[i] & 0xF];
  if ( v1 != 43 )
    explode_bomb();
}

void __fastcall phase_6(char *input)
{
  signed int i; // er12
  int j; // ebx
  signed int k; // esi
  listNode *v4; // rdx
  int v5; // eax
  listNode *v6; // rbx
  listNode *v7; // rcx
  signed int v8; // eax
  listNode *v9; // rdx
  signed int l; // er12
  listNode *pointers[6]; // [rsp+0h] [rbp-70h]
  int indices[6]; // [rsp+30h] [rbp-40h]

  read_six_numbers(input, indices);
  for ( i = 0; i <= 5; ++i )
    {
      if ( (unsigned int)(indices[i] - 1) > 5 )
        explode_bomb();
      for ( j = i + 1; j <= 5; ++j )
        {
          if ( indices[i] == indices[j] )
            explode_bomb();
        }
    }
  for ( k = 0; k <= 5; ++k )
    {
      v5 = 1;
      v4 = &node1;
      while ( indices[k] > v5 )
        {
          v4 = v4->next;
          ++v5;
        }
      pointers[k] = v4;
    }
  v6 = pointers[0];
  v7 = pointers[0];
  v8 = 1;
  while ( v8 <= 5 )
    {
      v9 = pointers[v8];
      v7->next = v9;
      ++v8;
      v7 = v9;
    }
  v7->next = 0LL;
  for ( l = 0; l <= 4; ++l )
    {
      if ( v6->value < v6->next->value )
        explode_bomb();
      v6 = v6->next;
    }
}
