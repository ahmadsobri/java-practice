# java-practice
java practice for beginer

Spring Java Annotation :

@Component
	- for marks the class as a bean or component 
	- so that the component-scanning mechanism can add it into application context
	
@ComponentScan (place in class that annotated @Configuration)
	- Packages to be scanned by Spring
	- is used with the @Configuration annotation to know the packages is annotated as @Component
	- parameter basePackageClasses=.. or basePackage=.. to scan

@Configuration (is an analog for an XML configuration file)
	- this is used on classes that define beans
	- Allows access to spring context
	- if you have class annotated @Component, you need @ComponentScan to this class that annotated @Configuration

@Bean
	- The annotation @Bean (annotated on method) works with @Configuration(annotated on top class) to create Spring beans

@Lazy (for component / configuration class)
	- for lazy load (the bean will be created and initialized only when it is first requested for)
	
@Value
	- for inject values from a property file into a bean’s attribute
	- It supports both #{...} and ${...} 

@Required
	- for mark the property is required
	
@Autowired
	- for inject interface
	
	
@Qualifier (used on @Autowired annotation place)
	- for qualify/choose the specific interface that want to inject (i.g : one interface implemented in multiple class)

@Controller(manual custom) / @RestController (include RequestMapping, ResponseBody)
	- layer for handle request and response from http client

@Service 
	- layer for business logic/ algorithm

@Repository
	- layer for handle DAO (Data Access Object) open/close DB connection, query dll




Design Pattern :

1. Builder 
	not use keyword new, but build(): create inner class Builder, with..() method return this, add method builld pass value to man class of it
	example :
		Class A = ClassA.builder().withId().withName().build();
		
2. Factory
	create abstract class, extends a class factory to it, in main class instance single factory class, call specific object by param value
	example :
		Factory factory = new Factory();
		ClassA a = factory.getFactory("A");
		ClassB b = factory.getFactory("B");
		
3. Singleton (single object/address in memmory) : reduce memmory
	create default constructor, create a static property with type of this class, create method that return this class.
	In this method check if instance is null then create new, else return instance. (class will just only one instance for reduce memmory)
	example :
		ClassA a = Singleton.getInstance();
		ClassB b = Singleton.getInstance();
		a.setText("A"); // print A
		b.setText("B"); // print A
		
4. Prototype (different object/address in memmory with clone value) : verry costly to instance
	not use keyword new, but clone(): create a class that implement Cloneable (java builtin class), create method clone() with type this class.
	In this method return (this class) super.clone() "with casting, dont forget use try cath exception"
	example :
	ClassA A1 = new ClassA("A1"); //return A1
	ClassA A1Clone = A1.clone(); // return A1
		
		
5. Object Pool
	a dynamic Object data type (T Generic type), create abstarct class<T>, int this class declare/initialize method with generic object (T).
	Create class that extends abstarct class, override that you need method.
	example :
		Abstract abstarct = new ClassImp();
		ClassA A1 = (ClassA) abstarct.create();
		ClassA A2 = (ClassA) abstarct.create();		
		abstarct.checkOut(A1);
		abstarct.checkIn(B1);
	
		
		
	