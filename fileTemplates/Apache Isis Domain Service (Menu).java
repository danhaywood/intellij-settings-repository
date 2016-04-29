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
        nature = org.apache.isis.applib.annotation.NatureOfService.VIEW_MENU_ONLY
)
@org.apache.isis.applib.annotation.DomainServiceLayout(
        named = "${MenuName}",
        menuOrder = "${menuOrder}"
)
public class ${EntityName}Menu {

#if (${domainEvents_y_or_n} == "y")    
    public static class ListAllDomainEvent extends ActionDomainEvent { }
#end    @org.apache.isis.applib.annotation.Action(
#if (${domainEvents_y_or_n} == "y")    
            domainEvent = ListAllDomainEvent.class,
#end            semantics = org.apache.isis.applib.annotation.SemanticsOf.SAFE,
            restrictTo = org.apache.isis.applib.annotation.RestrictTo.PROTOTYPING
    )
    @org.apache.isis.applib.annotation.ActionLayout(
            bookmarking = org.apache.isis.applib.annotation.BookmarkPolicy.AS_ROOT
    )
    @org.apache.isis.applib.annotation.MemberOrder(sequence = "1")
    public java.util.List<${EntityName}> listAll() {
        return ${entityName}repository.listAll();
    }

#if (${domainEvents_y_or_n} == "y")    
    public static class FindBy${Property}DomainEvent extends ActionDomainEvent { }
#end    @org.apache.isis.applib.annotation.Action(
#if (${domainEvents_y_or_n} == "y")    
            domainEvent = FindBy${Property}DomainEvent.class,
#end            semantics = org.apache.isis.applib.annotation.SemanticsOf.SAFE
    )
    @org.apache.isis.applib.annotation.ActionLayout(
            bookmarking = BookmarkPolicy.AS_ROOT
    )
    @org.apache.isis.applib.annotation.MemberOrder(sequence = "2")
    public java.util.List<${EntityName}> findBy${Property}(
            final ${PropertyType} ${property}
    ) {
        return ${entityName}repository.findBy${Property}Contains(${property});
    }

#if (${domainEvents_y_or_n} == "y")    
    public static class CreateDomainEvent extends ActionDomainEvent { }
#end    @org.apache.isis.applib.annotation.Action(
#if (${domainEvents_y_or_n} == "y")            domainEvent = CreateDomainEvent.class
#end    )
    @org.apache.isis.applib.annotation.MemberOrder(sequence = "3")
    public ${EntityName} create(
            final ${PropertyType} ${property}) {
        return ${entityName}repository.create(${property});
    }


#if (${domainEvents_y_or_n} == "y")    
    //region > domain events
    public static class PropertyDomainEvent<T>
            extends org.apache.isis.applib.services.eventbus.PropertyDomainEvent<${EntityName}Menu,T> {
    }
    public static class CollectionDomainEvent<T>
            extends org.apache.isis.applib.services.eventbus.CollectionDomainEvent<${EntityName}Menu,T> {
    }
    public static class ActionDomainEvent
            extends org.apache.isis.applib.services.eventbus.ActionDomainEvent<${EntityName}Menu> {
    }
    //endregion
#end

    @javax.inject.Inject
    ${EntityName}Repository ${entityName}repository;
}
