create or replace function to_pid18(pid15 in varchar2) return char is

  TYPE array_17_number IS VARRAY(17) OF NUMBER;
  TYPE array_11_char IS VARRAY(11) OF char;

  result         varchar2(18);
  v_check_number integer := 0;
  v_check_char   char(1);
  v_factor       array_17_number := array_17_number(7,
                                                    9,
                                                    10,
                                                    5,
                                                    8,
                                                    4,
                                                    2,
                                                    1,
                                                    6,
                                                    3,
                                                    7,
                                                    9,
                                                    10,
                                                    5,
                                                    8,
                                                    4,
                                                    2);

  v_mod array_11_char := array_11_char('1',
                                       '0',
                                       'X',
                                       '9',
                                       '8',
                                       '7',
                                       '6',
                                       '5',
                                       '4',
                                       '3',
                                       '2');
begin
  if (length(pid15) = 18) then
    return pid15;
  elsif (length(pid15) = 15) then
    result := substr(pid15, 1, 6) || '19' || substr(pid15, 7, 9);
    FOR i IN 1 .. 17 LOOP
      v_check_number := to_number(substr(result, i, 1)) * v_factor(i) +
                        v_check_number;
    END LOOP;
  
    v_check_number := mod(v_check_number, 11);
    v_check_char   := v_mod(v_check_number + 1);
    result         := result || v_check_char;
    return result;
  else
    raise_application_error(-20001, 'Length of pid should be 15 or 18!');
  end if;
end to_pid18;
