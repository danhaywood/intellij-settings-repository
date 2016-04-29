#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import org.apache.isis.applib.annotation.*;
import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;

#set( 
$viewModelName = 
$ViewModelName.substring(0,1).toLowerCase().concat($ViewModelName.substring(1)) 
)

#set( 
$Property = 
$property.substring(0,1).toUpperCase().concat($property.substring(1)) 
)

@ViewModel
public class ${ViewModelName} {

    public static class ${Property}DomainEvent
            extends org.apache.isis.applib.services.eventbus.PropertyDomainEvent<${ViewModelName},${PropertyType}> {
    }
    @org.apache.isis.applib.annotation.Property(
        domainEvent=${Property}DomainEvent.class
    )
    @lombok.Getter @lombok.Setter
    private ${PropertyType} ${property};

}
