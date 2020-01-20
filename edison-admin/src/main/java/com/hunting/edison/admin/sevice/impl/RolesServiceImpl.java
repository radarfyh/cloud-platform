package com.hunting.edison.admin.sevice.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hunting.edison.admin.constants.SysConstants;

import com.hunting.edison.admin.domain.ACLs;
import com.hunting.edison.admin.domain.Resources;
import com.hunting.edison.admin.domain.Roles;
import com.hunting.edison.admin.repository.AclsRepository;
import com.hunting.edison.admin.repository.ResourcesRepository;
import com.hunting.edison.admin.repository.RolesRepository;
import com.hunting.edison.admin.sevice.RolesService;
import com.hunting.edison.core.page.MyPageHelper;
import com.hunting.edison.core.page.MyPageRequest;
import com.hunting.edison.core.page.MyPageResult;

/**
 * role管理
 * @author Louis & Edison
 * @date 2020/1/9
 */
@Service
public class RolesServiceImpl  implements RolesService {
	private static Logger log = LogManager.getLogger(RolesServiceImpl.class);

	@Resource
	private RolesRepository rolesRepo;
	@Resource
	private AclsRepository aclsRepo;
	@Resource
	private ResourcesRepository resourcesRepo;
	
	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Roles record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Integer id, Roles record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Roles record) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Iterable<Roles> findAllSort(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Roles> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Roles record) {

		rolesRepo.save(record);
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(Roles record) {
		rolesRepo.deleteById(record.getId());
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(List<Roles> records) {
		for(Roles record:records) {
			delete(record);
		}
		return SysConstants.SUCCUSS;
	}

	@Override
	public Roles findById(Integer id) {
		Optional<Roles> role = rolesRepo.findById(id);
		if(role.isPresent()) {
			return role.get();
		}
		return null;
	}

	@Override
	public MyPageResult findPage(MyPageRequest pageRequest) {
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageRequest.toJSON());

		Page<Roles> result = null;
		String name = MyPageHelper.getColumnFilterValue(pageRequest, "name");
		
		Sort sort = Sort.by(Sort.Direction.DESC, "name");
		Pageable page = PageRequest.of(pageRequest.getPageNum() - 1, pageRequest.getPageSize(), sort);
		if(name != null && !name.isEmpty()) {
			result = rolesRepo.findByName(name, page);
		} else {
			result = rolesRepo.findAll(page);
		}
				
		// 转换格式
		MyPageResult pageResult = MyPageHelper.getPageResult(result);
		
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageResult.toJSON());
				
		return pageResult;
	}

	@Override
	public List<Roles> findAll() {
		return rolesRepo.findAll();
	}
	
	public Iterable<Roles> findIterable() {
		return rolesRepo.findAll();
	}

	public RolesRepository getRolesRepo() {
		return rolesRepo;
	}

	public void setRolesRepo(RolesRepository rolesRepo) {
		this.rolesRepo = rolesRepo;
	}

	@Override
	public List<Resources> findRoleMenus(Integer roleId) {
		Optional<Roles> role = rolesRepo.findById(roleId);
		if(role.isPresent() && SysConstants.ADMIN.equalsIgnoreCase(role.get().getName())) {
			// 如果是超级管理员，返回全部
			return resourcesRepo.findAll();
		}
		List<Resources> resources = new ArrayList<>();
		List<ACLs> acls = aclsRepo.findByRoleId(roleId);
		for(ACLs acl:acls) {
			Optional<Resources> resource = resourcesRepo.findById(acl.getResourceId());
			if(resource.isPresent()) {
				resources.add(resource.get());
			}
		}
		//调试信息
		log.log(Level.forName("NOTICE", 450),resources);
		
		return resources;
	}

	@Transactional
	@Override
	public int saveRoleMenus(List<ACLs> records) {
		if(records == null || records.isEmpty()) {
			return 1;
		}
		Integer roleId = records.get(0).getRoleId(); 
		aclsRepo.deleteByRoleId(roleId);
		for(ACLs record:records) {
			aclsRepo.save(record);
		}
		return SysConstants.SUCCUSS;
	}

	@Override
	public List<Roles> findByName(String name) {
		return rolesRepo.findByName(name);
	}
	
}
