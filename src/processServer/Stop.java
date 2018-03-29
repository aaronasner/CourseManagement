package processServer;

import authenticatedUsers.LoggedInAuthenticatedUser;

public class Stop extends IOperation {
    public LoggedInAuthenticatedUser perform(LoggedInAuthenticatedUser user){
        SystemState.status = false;
        return user;
    }
}
