package com.alu.itoken.service.sso.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alu.itoken.service.sso.entity.UPermission;
import com.alu.itoken.service.sso.entity.UUserRole;
import com.alu.itoken.service.sso.entity.UserInfo;
import com.alu.itoken.service.sso.mapper.UserInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import lombok.extern.slf4j.Slf4j;

//import com.xm.shiro.admin.dao.UPermissionDao;
//import com.xm.shiro.admin.dao.URoleDao;
//import com.xm.shiro.admin.dao.UUserDao;
//import com.xm.shiro.admin.entity.UPermission;
//import com.xm.shiro.admin.entity.URole;
//import com.xm.shiro.admin.entity.UUser;


/**
 * 获取用户的角色和权限信息
 * Created by bamboo on 2017/5/10.
 */
@Slf4j
public class ShiroRealm 	//extends AuthorizingRealm
{

    


    //一般这里都写的是servic，我省略了service的接口和实现方法直接调用的dao
	@Autowired
	private UserInfoMapper userInfoMapper;
//    @Autowired
//    private RoleMapper roleMapper;
//    @Autowired
//    private PermissionMapper permissionMapper;


   // @Override
 //   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
//        //查出是否有此用户
//        UserInfo userInfo = new UserInfo();
//        userInfo.setName(token.getUsername());
//        QueryWrapper queryRapper = new QueryWrapper();
//        queryRapper.setEntity(userInfo)
//   //     UserInfo hasUser = userInfoMapper.(queryRapper);
//        String md5Pwd = new Md5Hash("123", "lucare",2).toString();
//        if (hasUser != null) {
//            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//            List<UUserRole> rlist = userInfoMapper.findRoleByUid(hasUser.getId());//获取用户角色
//            List<UPermission> plist = userInfoMapper.findPermissionByUid(hasUser.getId());//获取用户权限
//            List<String> roleStrlist=new ArrayList<String>();//用户的角色集合
//            List<String> perminsStrlist=new ArrayList<String>();//用户的权限集合
//            for (UUserRole role : rlist) {
//                roleStrlist.add(role.getName());
//            }
//            for (UPermission uPermission : plist) {
//                perminsStrlist.add(uPermission.getName());
//            }
////            hasUser.setUUserROle(roleStrlist);
////            hasUser.setUPermission(perminsStrlist);
//            Session session = SecurityUtils.getSubject().getSession();
//            session.setAttribute("user", hasUser);//成功则放入session
//            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
//            return new SimpleAuthenticationInfo(hasUser, hasUser.getPassword(), getName());
//        }
//        return null;
//    }

    /**
     * 权限认证
     *
     * @param principalCollection
     * @return
     */
  //  @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        
        //获取当前登录输入的用户名，等价于(String) principalCollection.fromRealm(getName()).iterator().next();
//        String loginName = (String) super.getAvailablePrincipal(principalCollection);
    	UserInfo user = (UserInfo) principalCollection.getPrimaryPrincipal();
//        //到数据库查是否有此对象
//        User user = null;// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        user = userMapper.findByName(loginName);
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
//            info.addRoles(user.getUUserROle());
//            //用户的权限集合
//            info.addStringPermissions(user.getUPermission());

            return info;
        }
        // 返回null的话，就会导致任何用户访问被拦截的请求时，都会自动跳转到unauthorizedUrl指定的地址
        return null;
    }


}


