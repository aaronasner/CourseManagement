package processServer;

import authenticatedUsers.LoggedInAuthenticatedUser;

/**
 * Created by JakesComp on 2018-03-28.
 */
public class Logout extends IOperation {
    public void perform(LoggedInAuthenticatedUser user){
        user = null;
    }
}
