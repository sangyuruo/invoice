package com.hl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hl.dao.ActionDao;
import com.hl.domain.Action;
import com.hl.domain.ActionQuery;
import com.hl.service.ActionService;

@Service("actionService")
public class ActionServiceImpl implements ActionService{

	@Resource(name = "actionDao")
	private ActionDao actionDao;
	
	@Override
	public ActionQuery getTwentyActionByTime(Integer page,String startTime,String endTime,Integer  section) {
		ActionQuery actionQuery = new ActionQuery();
		//一次获取二十条日志
		List<Action>actions = actionDao.getTwentyActionByTime(page,startTime,endTime,section);
		//获取总页数
		Integer sum = actionDao.getActionSumByTime(startTime,endTime);
		Integer page_sum = sum/section +1;
		actionQuery.setPage_sum(page_sum);
		actionQuery.setAction_list(actions);
		return actionQuery;
	}

	@Override
	public ActionQuery getTwentyActionByKeyword(Integer page, 
			String startTime, String endTime, String keyword,Integer type,Integer  section) {
		//如果page=0的话，同时返回总页数
		ActionQuery actionQuery = null;
		switch (type) {
		case 0://根据user_ip进行查询
			actionQuery = actionDao.getTwentyActionByKeywordIp(startTime,endTime,keyword,page,section);
			break;
		case 1://根据user_name查询
			actionQuery = actionDao.getTwentyActionByKeywordUserName(startTime,endTime,keyword,page,section);
			break;
		case 2://根据company_name
			actionQuery = actionDao.getTwentyActionByKeywordCompanyName(startTime,endTime,keyword,page,section);
			break;
		case 3://根据操作类型
			actionQuery = actionDao.getTwentyActionByKeywordDescription(startTime,endTime,keyword,page,section);
			break;
		default:
			break;
		}
		return actionQuery;
	}

	@Override
	public ActionQuery getTwentyActionInit(Integer page,Integer  section) {
		ActionQuery actionQuery = new ActionQuery();
		//一次获取二十条日志
		List<Action>actions = actionDao.getTwentyActionInit(page,section);
		//获取总页数
		if(page == 0){
			Integer sum = actionDao.getActionSumInit();
			Integer page_sum = sum/20 +1;
			actionQuery.setPage_sum(page_sum);
		}
		actionQuery.setAction_list(actions);
		return actionQuery;
	}


}
