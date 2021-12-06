package com.care.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;

import com.care.root.mapper.TestMapper;

@Service
public class TestServiceImpl {
	@Autowired TestMapper mapper;

	@Transactional //트랜잭셔널 처리
	public void buy(Model model, int num) {
		int[] result = {0,0};
		try {
			result[0] = mapper.userInsert(num);
			result[1] = mapper.systemInsert(num);
			//데이터가 2가지 저장됨.
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			//db처리할때 catch쪽으로 넘어가면, 즉 문제가 생기면, 데이터를 저장하지 않고 다시 롤백하겠다.
			//@Transactional + try&catch 여야만 사용 가능
			
			//db에 문제가 생길때, 저장되지 않도록 하기 위해 사용. db저장을 위해 사용함
			e.printStackTrace();
		}
		model.addAttribute("result",result);
	}

	public void dbResult(Model model) {
		model.addAttribute("user",mapper.userDbResult());
		model.addAttribute("system",mapper.systemDbResult());
	}
}
