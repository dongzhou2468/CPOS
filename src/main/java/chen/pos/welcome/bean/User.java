/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-14
 */
package chen.pos.welcome.bean;

import com.google.common.base.MoreObjects;

public class User {

    private int userId;
    private String email;
    private String name;
    private String salt;
    private String password;
    private int roleId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("email", email)
                .add("name", name)
                .add("salt", salt)
                .add("password", password)
                .add("roleId", roleId)
                .toString();
    }
}
