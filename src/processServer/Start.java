package processServer;

import authenticatedUsers.LoggedInAuthenticatedUser;

public class Start extends IOperation {

    public LoggedInAuthenticatedUser perform(LoggedInAuthenticatedUser user){
        SystemState.status = true;
        return user;
    }
}
