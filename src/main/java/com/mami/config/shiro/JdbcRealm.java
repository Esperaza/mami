//package com.mami.config.shiro;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//import javax.sql.DataSource;
//
//import org.apache.shiro.authc.AccountException;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UnknownAccountException;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.JdbcUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.mami.bean.User;
//
//public class JdbcRealm extends AuthorizingRealm {
//    private static final Logger log = LoggerFactory.getLogger(JdbcRealm.class);
//    /**
//     * The default query used to retrieve account data for the user.
//     */
//    protected final String AUTHENTICATION_QUERY = "select user_id,account,password,user_name,status from user where user_name = ?";
//    
//    /**
//     * The default query used to retrieve account data for the user when {@link #saltStyle} is COLUMN.
//     */
//    protected final String SALTED_AUTHENTICATION_QUERY = "select password, password_salt from user where user_name = ?";
//
//    /**
//     * The default query used to retrieve the roles that apply to a user.
//     */
//    protected final String USER_ROLES_QUERY = "select role_name from role where user_name = ?";
//
//    /**
//     * The default query used to retrieve permissions that apply to a particular role.
//     */
//    protected final String PERMISSIONS_QUERY = "select permission from permission where role_name = ?";
//
//    @Autowired
//    protected DataSource dataSource;
//
//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//
//        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
//        String username = upToken.getUsername();
//
//        // Null username is invalid
//        if (username == null) {
//            throw new AccountException("Null userName are not allowed by this realm.");
//        }
//        Connection conn = null;
//        SimpleAuthenticationInfo info = null;
//        try {
//            conn = dataSource.getConnection();
//
//            User user = getPasswordForUser(conn, username);
//            if (user.getPassword() == null) {
//                throw new UnknownAccountException("No account found for user [" + username + "]");
//            }
//            info = new SimpleAuthenticationInfo(user, user.getPassword().toCharArray(), getName());
//        } catch (SQLException e) {
//            final String message = "There was a SQL error while authenticating user [" + username + "]";
//            if (log.isErrorEnabled()) {
//                log.error(message, e);
//            }
//            // Rethrow any SQL errors as an authentication exception
//            throw new AuthenticationException(message, e);
//        } finally {
//            JdbcUtils.closeConnection(conn);
//        }
//        return info;
//	}
//    private User getPasswordForUser(Connection conn, String username) throws SQLException {
//
//        User user = null;
//        
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            ps = conn.prepareStatement(this.AUTHENTICATION_QUERY);
//            ps.setString(1, username);
//
//            // Execute query
//            rs = ps.executeQuery();
//
//            // Loop over results - although we are only expecting one result, since usernames should be unique
//            boolean foundResult = false;
//            while (rs.next()) {
//
//                // Check to ensure only one row is processed
//                if (foundResult) {
//                    throw new AuthenticationException("More than one user row found for user [" + username + "]. Usernames must be unique.");
//                }
//                user = new User();
//                user.setUserId(rs.getInt(1));
//                user.setAccount(rs.getString(2));
//                user.setPassword(rs.getString(3));
//                user.setUserName(rs.getString(4));
//                user.setStatus(rs.getString(5));
//            }
//        } finally {
//            JdbcUtils.closeResultSet(rs);
//            JdbcUtils.closeStatement(ps);
//        }
//
//        return user;
//    }
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        if (principals == null) {
//            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
//        }
//
//        User user = (User) getAvailablePrincipal(principals);
//
//        Connection conn = null;
//        Set<String> permissions = new HashSet<String>();
//        permissions.add("admin");
//        try {
//            conn = dataSource.getConnection();
//        } catch (SQLException e) {
//            final String message = "There was a SQL error while authorizing user [" + user.getUserName() + "]";
//            if (log.isErrorEnabled()) {
//                log.error(message, e);
//            }
//
//            // Rethrow any SQL errors as an authorization exception
//            throw new AuthorizationException(message, e);
//        } finally {
//            JdbcUtils.closeConnection(conn);
//        }
//
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(permissions);
////        info.setStringPermissions(permissions);
//        return info;
//	}
//
//    protected Set<String> getRoleNamesForUser(Connection conn, String username) throws SQLException {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        Set<String> roleNames = new LinkedHashSet<String>();
//        try {
//            ps = conn.prepareStatement(this.USER_ROLES_QUERY);
//            ps.setString(1, username);
//
//            // Execute query
//            rs = ps.executeQuery();
//
//            // Loop over results and add each returned role to a set
//            while (rs.next()) {
//
//                String roleName = rs.getString(1);
//
//                // Add the role to the list of names if it isn't null
//                if (roleName != null) {
//                    roleNames.add(roleName);
//                } else {
//                    if (log.isWarnEnabled()) {
//                        log.warn("Null role name found while retrieving role names for user [" + username + "]");
//                    }
//                }
//            }
//        } finally {
//            JdbcUtils.closeResultSet(rs);
//            JdbcUtils.closeStatement(ps);
//        }
//        return roleNames;
//    }
//
//    protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames) throws SQLException {
//        PreparedStatement ps = null;
//        Set<String> permissions = new LinkedHashSet<String>();
//        try {
//            ps = conn.prepareStatement(this.PERMISSIONS_QUERY);
//            for (String roleName : roleNames) {
//
//                ps.setString(1, roleName);
//
//                ResultSet rs = null;
//
//                try {
//                    // Execute query
//                    rs = ps.executeQuery();
//
//                    // Loop over results and add each returned role to a set
//                    while (rs.next()) {
//
//                        String permissionString = rs.getString(1);
//
//                        // Add the permission to the set of permissions
//                        permissions.add(permissionString);
//                    }
//                } finally {
//                    JdbcUtils.closeResultSet(rs);
//                }
//
//            }
//        } finally {
//            JdbcUtils.closeStatement(ps);
//        }
//
//        return permissions;
//    }
//
//}
