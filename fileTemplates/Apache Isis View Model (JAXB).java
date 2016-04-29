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

@javax.xml.bind.annotation.XmlRootElement(name = "${viewModelName}")
@javax.xml.bind.annotation.XmlType(
        propOrder = {
            "${property}",
        }
)
@javax.xml.bind.annotation.XmlAccessorType(XmlAccessType.PROPERTY)
@org.apache.isis.applib.annotation.DomainObjectLayout(
        titleUiEvent = org.apache.isis.applib.services.eventbus.TitleUiEvent.Default.class
)
public class ${ViewModelName} implements org.apache.isis.applib.services.dto.Dto {

    @lombok.Getter @lombok.Setter
    private ${PropertyType} ${property};

}
