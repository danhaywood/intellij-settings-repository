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

#set( 
$packageNameLastPeriodChar = 
$PACKAGE_NAME.lastIndexOf(".") 
)

#set( 
$packageNameLastPeriodCharPlus1 = 
$packageNameLastPeriodChar + 1 
)

#set( 
$schema = 
$PACKAGE_NAME.substring($packageNameLastPeriodCharPlus1) 
)

@javax.jdo.annotations.PersistenceCapable(
        identityType= javax.jdo.annotations.IdentityType.DATASTORE,
        schema = "$schema",
        table = "$EntityName"
)
@javax.jdo.annotations.DatastoreIdentity(
        strategy=javax.jdo.annotations.IdGeneratorStrategy.IDENTITY,
        column="id")
@javax.jdo.annotations.Version(
        strategy= javax.jdo.annotations.VersionStrategy.VERSION_NUMBER,
        column="version")
@javax.jdo.annotations.Queries({
        @javax.jdo.annotations.Query(
                name = "find", language = "JDOQL",
                value = "SELECT "
                        + "FROM ${PACKAGE_NAME}.$EntityName "),
        @javax.jdo.annotations.Query(
                name = "findBy${Property}Contains", language = "JDOQL",
                value = "SELECT "
                        + "FROM ${PACKAGE_NAME}.$EntityName "
                        + "WHERE ${property}.indexOf(:${property}) >= 0 "),
        @javax.jdo.annotations.Query(
                name = "findBy${Property}", language = "JDOQL",
                value = "SELECT "
                        + "FROM ${PACKAGE_NAME}.$EntityName "
                        + "WHERE $property == :$property ")
})
@javax.jdo.annotations.Unique(name="${EntityName}_${property}_UNQ", members = {"$property"})
#if (${jaxbAdapter_y_or_n} == "y")@javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter(org.apache.isis.schema.utils.jaxbadapters.PersistentEntityAdapter.class)
#end @org.apache.isis.applib.annotation.DomainObject(
        editing = Editing.DISABLED
)
@org.apache.isis.applib.annotation.DomainObjectLayout(
        bookmarking = BookmarkPolicy.AS_ROOT
)
public class ${EntityName} implements Comparable<${EntityName}> {

            

#if (${domainEvents_y_or_n} == "y") 
    public static class ${Property}DomainEvent extends PropertyDomainEvent<${PropertyType}> {
    }
#end    @javax.jdo.annotations.Column(allowsNull = "false")
    @org.apache.isis.applib.annotation.Property( #if (${domainEvents_y_or_n} == "y")        domainEvent=${Property}DomainEvent.class
    #end)
    @lombok.Getter @lombok.Setter
    private ${PropertyType} ${property};



    //region > compareTo, toString
    @Override
    public int compareTo(final ${EntityName} other) {
        return org.apache.isis.applib.util.ObjectContracts.compare(this, other, "${property}");
    }
    @Override
    public String toString() {
        return org.apache.isis.applib.util.ObjectContracts.toString(this, "${property}");
    }
    //endregion


#if (${domainEvents_y_or_n} == "y")    
    //region > domain events
    public static abstract class PropertyDomainEvent<T>
            extends org.apache.isis.applib.services.eventbus.PropertyDomainEvent<${EntityName},T> {
    }
    public static abstract class CollectionDomainEvent<T>
            extends org.apache.isis.applib.services.eventbus.CollectionDomainEvent<${EntityName},T> {
    }
    public static abstract class ActionDomainEvent
            extends org.apache.isis.applib.services.eventbus.ActionDomainEvent<${EntityName}> {
    }
    //endregion
#end
}
