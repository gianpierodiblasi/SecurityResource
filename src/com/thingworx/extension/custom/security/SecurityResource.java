package com.thingworx.extension.custom.security;

import com.thingworx.entities.utils.EntityUtilities;
import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.relationships.RelationshipTypes;
import com.thingworx.resources.Resource;
import com.thingworx.security.context.SecurityContext;
import com.thingworx.security.users.User;
import com.thingworx.webservices.context.ThreadLocalContext;
import org.slf4j.Logger;

public class SecurityResource extends Resource {

  private final static Logger SCRIPT_LOGGER = LogUtilities.getInstance().getScriptLogger(SecurityResource.class);
  private static final long serialVersionUID = 1L;

  @ThingworxServiceDefinition(name = "switchToUser", description = "", category = "", isAllowOverride = false, aspects = {"isAsync:false"})
  @ThingworxServiceResult(name = "result", description = "true if the user switching has been performed, false otherwise", baseType = "BOOLEAN", aspects = {})
  public Boolean switchToUser(@ThingworxServiceParameter(name = "user", description = "", baseType = "USERNAME", aspects = {"isRequired:true"}) String user) throws Exception {
    boolean result;
    SCRIPT_LOGGER.debug("SecurityResource - switchToUser -> Start");
    User userEntity = (User) EntityUtilities.findEntity(user, RelationshipTypes.ThingworxRelationshipTypes.User);

    if (userEntity != null) {
      SecurityContext userContext = SecurityContext.createUserContext(userEntity);
      ThreadLocalContext.setSecurityContext(userContext);
      result = true;
    } else {
      SCRIPT_LOGGER.error("SecurityResource - switchToUser -> User " + user + " is not visible by current user");
      result = false;
    }
    SCRIPT_LOGGER.debug("SecurityResource - switchToUser -> Stop");
    return result;
  }

  @ThingworxServiceDefinition(name = "comeBackToCurrentUser", description = "", category = "", isAllowOverride = false, aspects = {"isAsync:false"})
  public void comeBackToCurrentUser() throws Exception {
    SCRIPT_LOGGER.debug("SecurityResource - unrun -> Start");
    ThreadLocalContext.clearSecurityContext();
    SCRIPT_LOGGER.debug("SecurityResource - unrun -> Stop");
  }
}
