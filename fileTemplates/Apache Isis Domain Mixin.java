#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import org.apache.isis.applib.annotation.*;
import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;

#set( 
$mixedInType = 
$MixedInType.substring(0,1).toLowerCase().concat($MixedInType.substring(1)) 
)

#set( 
$Action = 
$action.substring(0,1).toUpperCase().concat($action.substring(1)) 
)

@org.apache.isis.applib.annotation.Mixin
public class ${MixedInType}_${action} {

    private final ${MixedInType} ${mixedInType};
    
    public ${MixedInType}_${action}(${MixedInType} ${mixedInType}) {
        this.${mixedInType} = ${mixedInType};
    }
    
#if (${domainEvents_y_or_n} == "y")    public static class ${Action}DomainEvent extends ActionDomainEvent {}
#end
    @org.apache.isis.applib.annotation.Action(#if (${domainEvents_y_or_n} == "y")
        domainEvent = ${Action}DomainEvent.class
    #end)
    public ${MixedInType} ${DS}${DS}(final ${ParameterType} ${parameterName}) {
        // TODO: complete implementation
        return ${mixedInType};
    }
    
#if (${domainEvents_y_or_n} == "y")    public static class ActionDomainEvent
            extends org.apache.isis.applib.services.eventbus.ActionDomainEvent<${MixedInType}> {
    }
#end    
}
