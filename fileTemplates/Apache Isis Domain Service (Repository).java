#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import org.apache.isis.applib.annotation.*;
import javax.jdo.annotations.*;
import lombok.Getter;
import lombok.Setter;

#set( 
$entityName = 
$EntityName.substring(0,1).toLowerCase().concat($EntityName.substring(1)) 
)

#set( 
$Property = 
$property.substring(0,1).toUpperCase().concat($property.substring(1)) 
)

@org.apache.isis.applib.annotation.DomainService(
        nature = org.apache.isis.applib.annotation.NatureOfService.DOMAIN,
        repositoryFor = ${EntityName}.class
)
public class ${EntityName}Repository {

    @org.apache.isis.applib.annotation.Programmatic
    public java.util.List<$EntityName> listAll() {
        return container.allInstances(${EntityName}.class);
    }

    @org.apache.isis.applib.annotation.Programmatic
    public $EntityName findBy${Property}(
            final String ${property}
    ) {
        return container.uniqueMatch(
                new org.apache.isis.applib.query.QueryDefault<>(
                        ${EntityName}.class,
                        "findBy${Property}",
                        "${property}", ${property}));
    }

    @org.apache.isis.applib.annotation.Programmatic
    public java.util.List<${EntityName}> findBy${Property}Contains(
            final ${PropertyType} ${property}
    ) {
        return container.allMatches(
                new org.apache.isis.applib.query.QueryDefault<>(
                        ${EntityName}.class,
                        "findBy${Property}Contains",
                        "${property}", ${property}));
    }

    @org.apache.isis.applib.annotation.Programmatic
    public $EntityName create(final ${PropertyType} $property) {
        final $EntityName $entityName = container.newTransientInstance(${EntityName}.class);
        ${entityName}.set${Property}(${property});
        container.persistIfNotAlready(${entityName});
        return ${entityName};
    }

    @org.apache.isis.applib.annotation.Programmatic
    public $EntityName findOrCreate(
            final ${PropertyType} ${property}
    ) {
        $EntityName ${entityName} = findBy${Property}(${property});
        if(${entityName} == null) {
            ${entityName} = create(${property});
        }
        return ${entityName};
    }


    @javax.inject.Inject 
    org.apache.isis.applib.DomainObjectContainer container;
}
