
package entity;

/**
 *
 * @author fpt shop
 */
public class Account {
    private int uID;
    private String user;
    private String pass;
    private int isStaff;
    private int isAdmin;

    public Account() {
    }

    public Account(int uID, String user, String pass, int isStaff, int isAdmin) {
        this.uID = uID;
        this.user = user;
        this.pass = pass;
        this.isStaff = isStaff;
        this.isAdmin = isAdmin;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(int isStaff) {
        this.isStaff = isStaff;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "Account{" + "uID=" + uID + ", user=" + user + ", pass=" + pass + ", isStaff=" + isStaff + ", isAdmin=" + isAdmin + '}';
    }
    
    
}
