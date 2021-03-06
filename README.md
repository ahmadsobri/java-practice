Spring Java Annotation :\
https://dzone.com/articles/a-guide-to-spring-framework-annotations

# Spring Annotation

**@Component**\
	- for marks the class as a bean or component \
	- so that the component-scanning mechanism can add it into application context
	
**@ComponentScan(basePackageClasses=..)** : place in class that annotated @Configuration\
	- Packages to be scanned by Spring\
	- is used with the @Configuration annotation to know the packages is annotated as @Component\
	- parameter basePackageClasses=.. or basePackage=.. to scan

**@Configuration** (is an analog for an XML configuration file)\
	- this is used on classes that define beans\
	- Allows access to spring context\
	- if you have class annotated @Component, you need @ComponentScan to this class that annotated @Configuration

**@Bean(name=..optional)**\
	- The annotation @Bean (annotated on method) works with @Configuration(annotated on top class) to create Spring beans

**@Lazy** (for component / configuration class)\
	- for lazy load (the bean will be created and initialized only when it is first requested for)
	
**@Value(statement)**\
	- for inject values from a property file into a bean’s attribute\
	- It supports both #{...} and ${...} 

**@Order(number)** \
	- for order Filter by number, i/g: if use many "Filter" n want to order specific run the filter by number

**@Required**\
	- for mark the property (usualy in parameter) is required
	
**@Autowired**\
	- for inject component/interface if use
	
**@Qualifier** (used on @Autowired annotation place)\
	- for qualify/choose the specific interface that want to inject (e.g : one interface implemented in multiple class)

**@ConditionalOnProperty(prefix = "config", name = "enable-prod", havingValue = "true")**\
	- for enable/disable class/component with custom remak in application.properties, \
	- e.g: by asign true/false to change prod/dev mode : "config.enable-prod=true"

**@Controller**(manual custom) / **@RestController** (include RequestMapping, ResponseBody)\
	- layer for handle request and response from http client

**@Service** \
	- layer for business logic/ algorithm

**@Repository**\
	- layer for handle DAO (Data Access Object) open/close DB connection, query dll


===org.springframework.data.jpa.repository.Query===

**@Query(value= "query", nativeQuery= bool)**\
	- execute query return value to the property/method


===javax.validation.constraints===
**@NotBlank**\
	- for validation property not blank (usualy in model), handle the request that using this model

**@Pattern(regexp = "^refresh_token$", message = "not valid")**\
	- for validation property with pattern (usualy in model), handle the request that using this model

**@Valid** \
	- for enable validation has been set in property of model/class:\
	- e.g : @valid ClassModel classA



# Design Pattern

## Creation Pattern

1. **Builder** \
	not use keyword new, but build(): create inner class Builder, with..() method return this, add method builld pass value to man class of it\
	example :
	```
		Class A = ClassA.builder().withId().withName().build();
	```
		
		
2. **Factory**\
	create abstract class, extends a class factory to it, in main class instance single factory class, call specific object by param value\
	example :
	```
		Factory factory = new Factory();
		ClassA a = factory.getFactory("A");
		ClassB b = factory.getFactory("B");
	```
		
3. **Singleton** (single object/address in memmory) : reduce memmory\
	create default constructor, create a static property with type of this class, create method that return this class.\
	In this method check if instance is null then create new, else return instance. (class will just only one instance for reduce memmory)\
	example :
	```
		ClassA a = Singleton.getInstance();
		ClassB b = Singleton.getInstance();
		a.setText("A"); // print A\
		b.setText("B"); // print A
	```
		
4. **Prototype** (different object/address in memmory with clone value) : verry costly to instance\
	not use keyword new, but clone(): create a class that implement Cloneable (java builtin class), create method clone() with type this class.\
	In this method return (this class) super.clone() "with casting, dont forget use try cath exception"\
	example :
	```
	ClassA A1 = new ClassA("A1"); //return A1
	ClassA A1Clone = A1.clone(); // return A1
	```
		
		
5. **Object Pool**\
	a dynamic Object data type (T Generic type), create abstarct class<T>, int this class declare/initialize method with generic object (T).\
	Create class that extends abstarct class, override that you need method.\
	example :
	```
		Abstract abstarct = new ClassImp();
		ClassA A1 = (ClassA) abstarct.create();
		ClassA A2 = (ClassA) abstarct.create();		
		abstarct.checkOut(A1);
		abstarct.checkIn(B1);
	```

## Structural Pattern

		
6. **Adapter**\
	for adaption old structure of the service/class to the new, then assign new obj to old service/class if needed\
	once not possible to modify old service/class, because can introduce the new bug/issue.\
	example :
	```
		OldService{}
		NewService{
			NewService(OldClass, NewClass){}
		}
		OldService.toDo(NewService.getA());
	```
	
	
7. **Bridge**\
	is interface and abstarct class that minimize multiple instance of object.\
	example :
	```
		abstarct Store{ abstarct Colour getColour(); }
		Red impl Colour{}
		Bag ext Store{ Bag(Colour); }
		new Bag(new Red())
	```

8. **Composite**\
	using interface as type of object to be casting in specific class that implement this iterface. (to tree data structure)\
	example :
	```
		ClassA impl Color{}; ClassB impl Color{}
		Process {
			Process(Color color);
		}
		new Process((ClassA) color)
		new Process((ClassB) color)
	```
	
9. **Decorator**
	for decoration the interface using abstract class. Interface as main reference that complete by abstarct class
	example :
	```
		Car imp Machine{}; 
		Navigation imp Machine{}; 
		Parking imp Machine{}
		abstract Decorator impl Machine {
			toDo()->return Machine.toDo()
		}
		Machine mch = Decorator(Navigation(Parking(Car())))
	```


10. **Flyweight**\
	reduce duplicate value on save to model for optimize object created during runtime (increase perform)\
	dont mix key and value that will be save, save value if it different another (key(value), value(value))\
	mapping index(key) to object value it. "no duplicate obj value, but mapping to another object"\
	example :
	```
		A.create(1, obj){
			X x = Q.getobj(obj); // add(check if is not available on list) and return obj by key(this value)
			Y y = T.mapobj(1,x); // mapping key/index to this obj value
		}
	```

11. **Proxy**\
	to handle scurity, manipulation layer request, \
	not call directly to main service, but override the service interface to another class, then do validation/checking process, if ok then call main service\
	example :\
	```
		ProxyService impl Iservice{
			void toDO(){
				...//handle validation
				if(true)->MainService.toDo();
			}
		}
	```
		
12. **Facade**\
	If You have a very complex application, and your objective is to hide the complexities from the client.\
	Example :
	```
	Computer imp Machine{}; 
	Robot imp Machine{}
	Facade {
		toDoComputer(){ Computer.toDo() }
		toDoRobot(){ Robot.toDO() }
	}
	Facade.toDoComputer(); Facade.toDoRobot();
	```
	
