package htb.cloudhosting.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "users", schema = "public")
@Entity
/* loaded from: cloudhosting-0.0.1.jar:BOOT-INF/classes/htb/cloudhosting/database/CozyUser.class */
public class CozyUser {
    @Id
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String role;

    public void setName(final String name) {
        this.name = name;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof CozyUser) {
            CozyUser other = (CozyUser) o;
            if (other.canEqual(this)) {
                Object this$name = getName();
                Object other$name = other.getName();
                if (this$name == null) {
                    if (other$name != null) {
                        return false;
                    }
                } else if (!this$name.equals(other$name)) {
                    return false;
                }
                Object this$password = getPassword();
                Object other$password = other.getPassword();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
                    return false;
                }
                Object this$role = getRole();
                Object other$role = other.getRole();
                return this$role == null ? other$role == null : this$role.equals(other$role);
            }
            return false;
        }
        return false;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CozyUser;
    }

    public int hashCode() {
        Object $name = getName();
        int result = (1 * 59) + ($name == null ? 43 : $name.hashCode());
        Object $password = getPassword();
        int result2 = (result * 59) + ($password == null ? 43 : $password.hashCode());
        Object $role = getRole();
        return (result2 * 59) + ($role == null ? 43 : $role.hashCode());
    }

    public String toString() {
        return "CozyUser(name=" + getName() + ", password=" + getPassword() + ", role=" + getRole() + ")";
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }
}