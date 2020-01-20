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

import com.hunting.edison.admin.constants.SysConstants;
import com.hunting.edison.admin.domain.ACLs;
import com.hunting.edison.admin.domain.Resources;
import com.hunting.edison.admin.domain.Roles;
import com.hunting.edison.admin.domain.Users;
import com.hunting.edison.admin.repository.AclsRepository;
import com.hunting.edison.admin.repository.ResourcesRepository;
import com.hunting.edison.admin.repository.RolesRepository;
import com.hunting.edison.admin.repository.UsersRepository;
import com.hunting.edison.admin.sevice.ResourcesService;
import com.hunting.edison.core.page.MyPageHelper;
import com.hunting.edison.core.page.MyPageRequest;
import com.hunting.edison.core.page.MyPageResult;

/**
 * resource管理
 * @author Louis & Edison
 * @date 2020/1/9
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {
	private static Logger log = LogManager.getLogger(ResourcesServiceImpl.class);

	@Resource
	private ResourcesRepository resourcesRepo;
	@Resource
	private UsersRepository usersRepo;
	@Resource
	private RolesRepository rolesRepo;
	@Resource
	private AclsRepository aclsRepo;


	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Resources record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Integer id, Resources record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Resources record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<Resources> findAllSort(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Resources> findAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(Resources record) {
		if(record.getParentId() == null) {
			record.setParentId(0);
		}
		resourcesRepo.save(record);
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(Resources record) {
		resourcesRepo.deleteById(record.getId());
		return SysConstants.SUCCUSS;
	}

	@Override
	public int delete(List<Resources> records) {
		for(Resources record:records) {
			delete(record);
		}
		return SysConstants.SUCCUSS;
	}

	@Override
	public Resources findById(Integer id) {
		Optional<Resources> resource = resourcesRepo.findById(id);
		if(resource.isPresent()) {
			return resource.get();
		}
		return null;
	}

	@Override
	public MyPageResult findPage(MyPageRequest pageRequest) {
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageRequest.toJSON());

		Page<Resources> result = null;
		Pageable page = PageRequest.of(pageRequest.getPageNum() - 1, pageRequest.getPageSize());

		result = resourcesRepo.findAll(page);
				
		// 转换格式
		MyPageResult pageResult = MyPageHelper.getPageResult(result);
		//调试信息
		log.log(Level.forName("NOTICE", 450), pageResult.toJSON());

		return pageResult;
	}
	
	@Override
	public List<Resources> findTree(String userName, int menuType) {
		List<Resources> resources = new ArrayList<>();
		List<Resources> menus = findByUser(userName);
		for (Resources menu : menus) {
			if (menu.getParentId() == null || menu.getParentId() == 0) {
				menu.setLevel(0);
				if(!exists(resources, menu)) {
					resources.add(menu);
				}
			}
		}
		resources.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
		findChildren(resources, menus, menuType);
		return resources;
	}

	@Override
	public List<Resources> findByUser(String userName) {
		//获取用户
		if(userName == null || userName.isEmpty()) {
			userName = SysConstants.ADMIN; // 如果传入空，则当做管理员处理
		}
		List<Users> users = usersRepo.findByName(userName);
//		if(users.isEmpty()) {
//			return null;
//		}
		//获取角色
		List<Resources> resources = new ArrayList<>();
		for(Users user:users) {
			Optional<Roles> r = rolesRepo.findById(user.getId());
			if(r.isPresent()) {
				//超级管理员角色的默认code为admin，默认名称为“超级管理员”
				if(SysConstants.ADMIN.equalsIgnoreCase(r.get().getCode())) {
					// 如果是超级管理员，返回全部
					return resourcesRepo.findAll();
				}
				//获取权限
				List<ACLs> acls = aclsRepo.findByRoleId(r.get().getId());
				for(ACLs acl:acls) {
					//获取权限指向的资源
					Optional<Resources> resource = resourcesRepo.findById(acl.getResourceId());
					if(resource.isPresent()) {
						resources.add(resource.get());
					}
				}
			}
		}
		//调试信息
		log.log(Level.forName("NOTICE", 450),resources);
		
		return resources;
	}

	private void findChildren(List<Resources> SysMenus, List<Resources> menus, int menuType) {
		for (Resources SysMenu : SysMenus) {
			List<Resources> children = new ArrayList<>();
			for (Resources menu : menus) {
				if(menuType == 1 && menu.getResourceType() == 2) {
					// 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
					continue ;
				}
				if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
					menu.setParentName(SysMenu.getName());
					menu.setLevel(SysMenu.getLevel() + 1);
					if(!exists(children, menu)) {
						children.add(menu);
					}
				}
			}
			SysMenu.setChildren(children);
			children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
			findChildren(children, menus, menuType);
		}
	}

	private boolean exists(List<Resources> sysMenus, Resources sysMenu) {
		boolean exist = false;
		for(Resources menu:sysMenus) {
			if(menu.getId().equals(sysMenu.getId())) {
				exist = true;
			}
		}
		return exist;
	}
	
}
