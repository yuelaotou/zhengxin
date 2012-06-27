create or replace procedure initializtion as

  --------------------------------公积金余额用到的变量-------------------------
  v_emplyname          tb_emply_mng.emplyname%type; --职工姓名
  v_emplyacc           tb_emply_mng.emplyacc%type; --职工账号
  v_unitacc            tb_emply_mng.unitacc%type; --单位账号
  v_emplyid            tb_emply_mng.emplyid%type; --身份证号
  v_accblnce           tb_emply_mng.accblnce%type; --账户余额
  v_unitname           tb_unit_mng.unitname%type; --单位名称
  v_monthpay           tb_emply_mng.unithndinamnt%type; -- 月缴金额
  v_emply_st           char(20); --职工状态
  v_periodorgblnce     tb_emply_mng.periodorgblnce%type; --定期余额
  v_curbalance         tb_emply_mng.periodorgblnce%type; --活期余额
  v_emplyhndinamnt     tb_emply_mng.emplyhndinamnt%type; --其中个人部分
  v_enddate            char(6); --上次缴存年月
  v_nextpaymoney       tb_emply_mng.unithndinamnt%type; --下次月缴存额
  v_nextemplyhndinamnt tb_emply_mng.emplyhndinamnt%type; --其中个人部分
  v_opendate           char(8); --开户日期
  v_nextpaymonth       char(6); --下次汇缴月份
  v_lastbalance        tb_emplydetailacc_stat.accblnce%type; --上年余额
  v_lastinterest       tb_emplydetailacc_stat.accblnce%type; --上年利息
  v_cur_monthpay       lmk_gjjye.cur_monthpay%type; --本年汇缴合计
  v_cur_addpay         lmk_gjjye.cur_addpay%type; --本年补缴合计
  v_cur_pickmoney      lmk_gjjye.cur_pickmoney%type; --本年支取合计
  v_cur_month          char(2); --系统月份
  v_interestyear_start char(8); --结息年度开始
  v_interestyear_end   char(8); --结息年度结束
  v_biztype            char(10); --业务类型
  v_temp_money         lmk_gjjye.cur_pickmoney%type; --合计金额
  v_emplydetail_id     tb_emplydetailacc_stat.emplydetail_id%type; --上年结息id
  v_thisyearsum_num    number; --合计的条数，判断是否存在归集业务
  v_gjjmx_num          number; --公积金明细条数
  v_dkmx_num           number; --贷款明细条数
  -------------------------------公积金明细用到的变量----------------------------
  v_happendate    char(8); --日期,
  v_operationtype char(20); --摘要,
  v_loanamnt      tb_emplydetailacc_stat.loanamnt%type; --贷方,
  v_lendamnt      tb_emplydetailacc_stat.lendamnt%type; --借方,
  v_accblnce_mx   tb_emplydetailacc_stat.accblnce%type; --余额,
  v_hndindate     char(6); --汇缴年月
  v_occurmoney    tb_emplydetailacc_stat.loanamnt%type; --发生额
  v_cdflag        char(4);
  -------------------------------贷款余额用到的变量-------------------------------
  v_loanaccnum   number; --贷款账号个数
  v_temp_loanacc lmk_dkye.loan_kou_acc%type; --余额最大的贷款账号
  --------------------------------贷款明细用到的变量------------------------------
  v_operationdate char(8); --日期
  v_loan_type     char(20); --摘要
  v_backcorpus    tb_loan_emplydetailacc_stat.rovercorpus%type; --回收本金
  v_backinterest  tb_loan_emplydetailacc_stat.rshouldpayinterest%type; --回收利息
  v_realpay       tb_loan_emplydetailacc_stat.realpay%type; --还款金额
  v_amnt          tb_loan_emplydetailacc_stat.loan_amnt%type; --时点贷款余额

  --------------------------------   变量定义结束  --------------------------------
  Cursor cr_rq001_empId_orgid is
    select e.emplyacc  zgzh, --职工账号
           e.unitacc   dwzh, --单位账号
           e.emplyname zgxm, --职工姓名
           e.emplyid   sfzh --身份证号
      from tb_emply_mng e, tb_unit_mng u, rq001 r
     where e.unitacc = u.unitacc
       and r.name = e.emplyname
       and r.card_num = e.emplyid
       and r.empid = e.emplyacc;

  --账户信息游标
  Cursor cr_gjjye_1 is
    select e.accblnce, --账户余额
           u.unitname, --单位名称
           e.unithndinamnt + e.emplyhndinamnt, -- 月缴金额
           e.emply_st, --职工状态
           e.periodorgblnce, --定期余额
           e.accblnce - e.periodorgblnce, --活期余额
           e.emplyhndinamnt, --其中个人部分
           to_char(to_date(u.enddate, 'yyyy-mm'), 'yyyymm'), --上次缴存年月
           e.unithndinamnt + e.emplyhndinamnt, --下次月缴存额
           e.emplyhndinamnt, --其中个人部分
           to_char(e.opendate, 'yyyymmdd'), --开户日期
           to_char(add_months(to_date(u.enddate, 'yyyy-mm'), 1), 'yyyymm') --下次汇缴月份
      from tb_emply_mng e, tb_unit_mng u
     where e.unitacc = u.unitacc
       and e.emplyacc = v_emplyacc
       and e.unitacc = v_unitacc;

  --本年合计游标
  Cursor cr_gjjye_2 is
    select sum(es.loanamnt+es.lendamnt), es.operationtype
      from tb_emplydetailacc_stat es
     where es.operationtype in (0, 3, 5, 6)
       and es.happendate between
           to_date(v_interestyear_start, 'yyyy-mm-dd') and
           to_date(v_interestyear_end, 'yyyy-mm-dd')
       and es.emplyacc = v_emplyacc
       and es.unitacc = v_unitacc
     group by es.operationtype;

  --公积金明细查询
  Cursor cr_gjjmx is
    select to_char(t_start.happendate, 'yyyymmdd'), --日期,
           t_start.operationtype, --摘要,
           t_start.loanamnt, --贷方,
           t_start.lendamnt, --借方,
           t_start.accblnce, --余额,
           to_char(to_date(h_tran.hndindate, 'yyyy_mm'), 'yyyymm') --汇缴日期
      from tb_emplydetailacc_stat t_start, tb_hndin_tran h_tran
     where t_start.crdncenum = h_tran.hndincrdncenum(+)
       and t_start.emplyacc = v_emplyacc
       and t_start.unitacc = v_unitacc
     order by t_start.emplydetail_id asc;

  --贷款明细查询
  Cursor cr_dkhsmx is
    select to_char(t_loan_start.operationdate, 'yyyymmdd'), --日期,
           t_loan_start.loan_type, --摘要,
           t_loan_start.rovercorpus + t_loan_start.rthistermcorpus, -- 回收本金,
           t_loan_start.rshouldpayinterest + t_loan_start.rthisterminterest +
           t_loan_start.roverinterest, -- 回收利息,
           t_loan_start.realpay, -- 还款金额,
           t_loan_start.loan_amnt -- 时点贷款余额
      from tb_loan_emplydetailacc_stat t_loan_start
     where t_loan_start.loan_type != 0
       and t_loan_start.contract_code = v_temp_loanacc
       and t_loan_start.realpay <> 0
     order by t_loan_start.emplydetail_id;

begin
  open cr_rq001_empId_orgid;
  loop
    fetch cr_rq001_empId_orgid
      into v_emplyacc, v_unitacc, v_emplyname, v_emplyid;
    exit when cr_rq001_empId_orgid%notfound;
  
    --查询签约的职工编号和单位编号，进行循环，插入4张表：lmk_gjjye,lmk_gjjmx,lmk_dkye,lmk_dkhsmx----------
    delete from lmk_gjjye gjjye_t
     where gjjye_t.empid = v_emplyacc
       and gjjye_t.orgid = v_unitacc; -------------删除公积金余额表中该职工信息
    delete from lmk_gjjmx gjjmx_m
     where gjjmx_m.empid = v_emplyacc
       and gjjmx_m.orgid = v_unitacc; --------------删除公积金明细表中该职工信息
  
    select count(b.contract_code)
      into v_loanaccnum
      from tb_borrower_info b, tb_loan_info l
     where b.contract_code = l.contract_code
       and b.borrower_name = v_emplyname
       and b.borrower_id = v_emplyid; -------------该职工是否贷款
  
    if v_loanaccnum > 0 then
      select tt.code
        into v_temp_loanacc
        from (select b.contract_code code,
                     row_number() over(order by l.leave_corpus desc) mm
                from tb_borrower_info b, tb_loan_info l
               where b.contract_code = l.contract_code
                 and b.borrower_name = v_emplyname
                 and b.borrower_id = v_emplyid) tt
       where mm = 1; ----------------查询该职工贷款余额最大的贷款账号
    
      delete from lmk_dkye dkye_y
       where dkye_y.loan_kou_acc = v_temp_loanacc; --------------删除贷款余额表中该职工贷款余额最大的记录
      delete from lmk_dkhsmx dkhsmx_m
       where dkhsmx_m.loan_kou_acc = v_temp_loanacc; --------------删除贷款回收明细中该职工贷款余额最大的记录
    end if;
  
    -------------------------------公积金余额开始---------------------------------------------------------
    open cr_gjjye_1;
    fetch cr_gjjye_1
      into v_accblnce, --账户余额
    v_unitname, --单位名称
    v_monthpay, -- 月缴金额
    v_emply_st, --职工状态
    v_periodorgblnce, --定期余额
    v_curbalance, --活期余额
    v_emplyhndinamnt, --其中个人部分
    v_enddate, --上次缴存年月
    v_nextpaymoney, --下次月缴存额
    v_nextemplyhndinamnt, --其中个人部分
    v_opendate, --开户日期
    v_nextpaymonth --下次汇缴月份
    ;
    exit when cr_gjjye_1%notfound;
    close cr_gjjye_1;
    ------------------------------------职工状态转换开始-------------------------------------------------    
    if v_emply_st = '0' then
      v_emply_st := '正常';
    end if;
    if v_emply_st = '1' then
      v_emply_st := '封存';
    end if;
    if v_emply_st = '2' then
      v_emply_st := '调出';
    end if;
    if v_emply_st = '3' then
      v_emply_st := '销户';
    end if;
    if v_emply_st = '4' then
      v_emply_st := '资金转出';
    end if;
    if v_emply_st = '5' then
      v_emply_st := '资金转入';
    end if;
    if v_emply_st = '6' then
      v_emply_st := '销户提取';
    end if;
    if v_emply_st = '7' then
      v_emply_st := '删除';
    end if;
    if v_emply_st = '8' then
      v_emply_st := '调入';
    end if;
    ------------------------------------职工状态转换结束-------------------------------------------------
    ------------------------------------查询上年余额，上年利息开始---------------------------------------
  
    v_lastbalance  := 0;
    v_lastinterest := 0;
    select max(s.emplydetail_id)
      into v_emplydetail_id
      from tb_emplydetailacc_stat s
     where s.emplyacc = v_emplyacc
       and s.unitacc = v_unitacc
       and s.operationtype = 10;
    if v_emplydetail_id is not null then
      select s.loanamnt
        into v_lastbalance
        from tb_emplydetailacc_stat s
       where s.emplydetail_id = v_emplydetail_id;
      select s.accblnce - s.loanamnt
        into v_lastinterest
        from tb_emplydetailacc_stat s
       where s.emplydetail_id = v_emplydetail_id;
    end if;
  
    ------------------------------------查询上年余额，上年利息结束---------------------------------------
    ------------------------------------ 取结息年度开始 -------------------------------------------------
    select to_char(sysdate, 'mm') into v_cur_month from dual;
    if v_cur_month > '06' then
      select to_char(sysdate, 'yyyy') || '0701'
        into v_interestyear_start
        from dual;
      select to_char(sysdate, 'yyyy') + 1 || '0630'
        into v_interestyear_end
        from dual;
    else
      select to_char(sysdate, 'yyyy') - 1 || '0701'
        into v_interestyear_start
        from dual;
      select to_char(sysdate, 'yyyy') || '0630'
        into v_interestyear_end
        from dual;
    end if;
    ------------------------------------ 取结息年度结束 -------------------------------------------------
    -----------------------------查询本年合计开始（汇缴、补缴、支取）------------------------------------
    v_cur_pickmoney := 0;
    v_cur_monthpay  := 0;
    v_cur_addpay    := 0;
  
    select count(*)
      into v_thisyearsum_num
      from tb_emplydetailacc_stat es
     where es.operationtype in (0, 3, 5, 6)
       and es.happendate between
           to_date(v_interestyear_start, 'yyyy-mm-dd') and
           to_date(v_interestyear_end, 'yyyy-mm-dd')
       and es.emplyacc = v_emplyacc
       and es.unitacc = v_unitacc;
  
    if v_thisyearsum_num > 0 then
      open cr_gjjye_2;
      loop
        fetch cr_gjjye_2
          into v_temp_money, v_biztype;
        exit when cr_gjjye_2%notfound;
      
        if v_biztype = '0' then
          v_cur_monthpay := v_temp_money;
        end if;
        if v_biztype = '3' then
          v_cur_addpay := v_temp_money;
        end if;
        if v_biztype = '5' then
          v_cur_pickmoney := v_cur_pickmoney + v_temp_money;
        end if;
        if v_biztype = '6' then
          v_cur_pickmoney := v_cur_pickmoney + v_temp_money;
        end if;
      
      end loop;
      close cr_gjjye_2;
    end if;
    -----------------------------查询本年合计结束（汇缴、补缴、支取）------------------------------------
    --------------------------------------插入公积金余额表-----------------------------------------------
    insert into lmk_gjjye gjjye_table
      (gjjye_table.id, --主键id
       gjjye_table.balance, --公积金余额
       gjjye_table.emp_name, --职工姓名
       gjjye_table.card_num, --证件号码
       gjjye_table.org_name, --单位名称
       gjjye_table.last_pay_money, --最后一次缴存金额
       gjjye_table.empid, --职工账号
       gjjye_table.orgid, --单位账号
       gjjye_table.card_kind, --证件类型
       gjjye_table.acc_st, --账户状态
       gjjye_table.pre_balance, --往年余额
       gjjye_table.cur_balance, --本年余额
       gjjye_table.last_year_balance, --上年余额 
       gjjye_table.last_year_interest, --上年利息  
       gjjye_table.last_pay_emp, --最后一次个人部分
       gjjye_table.last_pay_month, --上次缴存年月
       gjjye_table.next_pay_money, --下次缴存额
       gjjye_table.next_pay_emp, --下次个人部分
       gjjye_table.cur_monthpay, --本年汇缴合计 
       gjjye_table.cur_addpay, --本年补缴合计 
       gjjye_table.cur_pickmoney, --本年支取合计  
       gjjye_table.opendate, --开户日期
       gjjye_table.next_pay_month) --下次缴存年月
    values
      (seq_lmkgjjye_id.nextval,
       v_accblnce,
       v_emplyname,
       v_emplyid,
       v_unitname,
       v_monthpay,
       v_emplyacc,
       v_unitacc,
       '身份证',
       v_emply_st,
       v_periodorgblnce,
       v_curbalance,
       v_lastbalance,
       v_lastinterest,
       v_emplyhndinamnt,
       v_enddate,
       v_nextpaymoney,
       v_nextemplyhndinamnt,
       v_cur_monthpay,
       v_cur_addpay,
       v_cur_pickmoney,
       v_opendate,
       v_nextpaymonth);
    ------------------------------------公积金余额结束-----------------------------------------------------
    ------------------------------------公积金明细开始-----------------------------------------------------
    select count(*)
      into v_gjjmx_num
      from tb_emplydetailacc_stat t_start, tb_hndin_tran h_tran
     where t_start.crdncenum = h_tran.hndincrdncenum(+)
       and t_start.emplyacc = v_emplyacc
       and t_start.unitacc = v_unitacc;
  
    if v_gjjmx_num > 0 then
      open cr_gjjmx;
      loop
        fetch cr_gjjmx
          into v_happendate, v_operationtype, v_loanamnt, v_lendamnt, v_accblnce_mx, v_hndindate;
        exit when cr_gjjmx%notfound;
      
        if v_operationtype = '0' then
          v_operationtype := '正常汇缴';
        end if;
        if v_operationtype = '1' then
          v_operationtype := '汇缴欠缴';
        end if;
        if v_operationtype = '2' then
          v_operationtype := '按比例汇缴';
        end if;
        if v_operationtype = '3' then
          v_operationtype := '补缴';
        end if;
        if v_operationtype = '4' then
          v_operationtype := '挂账';
        end if;
        if v_operationtype = '5' then
          v_operationtype := '部分提取';
        end if;
        if v_operationtype = '6' then
          v_operationtype := '销户提取';
        end if;
        if v_operationtype = '7' then
          v_operationtype := '资金转移';
        end if;
        if v_operationtype = '8' then
          v_operationtype := '转入确认';
        end if;
        if v_operationtype = '9' then
          v_operationtype := '错账调整';
        end if;
        if v_operationtype = '10' then
          v_operationtype := '年终结息';
        end if;
        if v_operationtype = '11' then
          v_operationtype := '上年结转';
        end if;
        if v_operationtype = '12' then
          v_operationtype := '结转下月';
        end if;
      
        v_occurmoney := 0;
        v_cdflag     := '';
      
        if v_loanamnt <> 0 then
          v_occurmoney := v_loanamnt;
          v_cdflag     := '贷';
        end if;
        if v_lendamnt <> 0 then
          v_occurmoney := v_lendamnt;
          v_cdflag     := '借';
        end if;
        insert into lmk_gjjmx gjjmx_table
          (gjjmx_table.id, --主键id
           gjjmx_table.orgid, --单位账号
           gjjmx_table.org_name, --单位名称
           gjjmx_table.empid, --职工账号
           gjjmx_table.emp_name, --职工姓名
           gjjmx_table.sett_date, --日期
           gjjmx_table.subtract, --摘要
           gjjmx_table.occur_money, --发生额
           gjjmx_table.balance, --余额
           gjjmx_table.credit_debit, --借贷标识
           gjjmx_table.pre_balance, --往年余额
           gjjmx_table.pay_month) --汇缴年月    
        values
          (seq_lmkgjjmx_id.nextval,
           v_unitacc,
           v_unitname,
           v_emplyacc,
           v_emplyname,
           v_happendate,
           v_operationtype,
           v_occurmoney,
           v_accblnce_mx,
           v_cdflag,
           '',
           v_hndindate);
      end loop;
      close cr_gjjmx;
    end if;
    ------------------------------------公积金明细结束-----------------------------------------------------
    ------------------------------------贷款余额开始-------------------------------------------------------
    if v_loanaccnum > 0 then
    
      insert into lmk_dkye dkye_table
        (dkye_table.id, --主键id
         dkye_table.over_loanmoney, --贷款余额
         dkye_table.emp_name, --职工姓名
         dkye_table.card_num, --身份证号
         dkye_table.org_name, --单位名称
         dkye_table.last_back_money, --还款金额
         dkye_table.loan_kou_acc, --贷款账号
         dkye_table.overdue_money, --逾期余额
         dkye_table.loan_money, --贷款本金
         dkye_table.all_back_money, --累计还款额
         dkye_table.over_month, --逾期期数
         dkye_table.remainder_limit, --剩余期限
         dkye_table.next_payday, --下期扣款日
         dkye_table.next_back_money) --下期扣款金额
        select seq_lmkdkye_id.nextval,
               t.unpay_corpus,
               b.borrower_name,
               b.borrower_id,
               b.unitname,
               (select le.rthistermcorpus + le.rthisterminterest
                  from tb_loan_emplydetailacc_stat le
                 where le.iscuroperation = 1
                   and le.contract_code = t.contract_code),
               t.contract_code,
               t.overcorpus,
               t.leave_corpus,
               (select le.rcorpus_total
                  from tb_loan_emplydetailacc_stat le
                 where le.iscuroperation = 1
                   and le.contract_code = t.contract_code),
               t.overmonths,
               t.leave_term,
               '无固定的扣款日',
               t.overcorpus + t.shouldpayinterest + t.thistermcorpus +
               t.thisterminterest
          from tb_loan_info t, tb_borrower_info b
         where t.contract_code = b.contract_code
           and t.contract_code = v_temp_loanacc;
    
    end if;
    ------------------------------------贷款余额结束-------------------------------------------------------
    ------------------------------------贷款明细开始-------------------------------------------------------
    if v_loanaccnum > 0 then
    
      select count(*)
        into v_dkmx_num
        from tb_loan_emplydetailacc_stat t_loan_start
       where t_loan_start.loan_type != 0
         and t_loan_start.contract_code = v_temp_loanacc
         and t_loan_start.realpay <> 0;
    
      if v_dkmx_num > 0 then
        open cr_dkhsmx;
        loop
          fetch cr_dkhsmx
            into v_operationdate, v_loan_type, v_backcorpus, v_backinterest, v_realpay, v_amnt;
          exit when cr_dkhsmx%notfound;
        
          if v_loan_type = '1' then
            v_loan_type := '逾期还款';
          end if;
          if v_loan_type = '2' then
            v_loan_type := '正常还款';
          end if;
          if v_loan_type = '3' then
            v_loan_type := '部分提前';
          end if;
          if v_loan_type = '4' then
            v_loan_type := '全部提前';
          end if;
        
          insert into lmk_dkhsmx dkhsmx_table
            (dkhsmx_table.id, --主键id
             dkhsmx_table.loan_kou_acc, --贷款账号 
             dkhsmx_table.sett_date, --结算日期 
             dkhsmx_table.subtract, --摘要（业务类型） 
             dkhsmx_table.call_back_coppu, --回收本金 
             dkhsmx_table.call_back_interest, --回收利息 
             dkhsmx_table.coppu_interest, --本息合计 
             dkhsmx_table.real_over_laonmoney) --时点贷款余额 
          
          values
            (seq_lmkdkhsmx_id.nextval,
             v_temp_loanacc,
             v_operationdate,
             v_loan_type,
             v_backcorpus,
             v_backinterest,
             v_realpay,
             v_amnt);
        
        end loop;
        close cr_dkhsmx;
      end if;
    end if;
    ------------------------------------贷款明细结束-------------------------------------------------------
  end loop;
  close cr_rq001_empId_orgid;
Exception
  when others then
    RAISE_APPLICATION_ERROR(-20000, '存储过程出现错误', true);
end initializtion;
/
