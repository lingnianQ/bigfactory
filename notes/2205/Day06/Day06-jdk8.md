#	JDK8简介
##	简述
Java 8由Oracle从2014年3月18日发布，此版本是自Java 5（发布于2004年）之后的一个重量级版本，也是java发展史上的一个里程碑式的版本。这个版本在JVM、编译器、库、Java语法特性等方面都做了很大改进，同时在语言的表达力、简洁性等个方面也有了很大的提高。目前几乎所有的JAVA框架也都已升级到支持JDK8，打开框架源码想了解其设计，假如不理解JDK8的这些特性看起来就会非常吃力。所以我们设计了这个专题，我们将在这个专题中讲解JDK8中的部分关键特性，并用实际案例讲解这些特性应用，希望同学们在JDK8技术的应用上有一个很好的提高。

##	新特性介绍

Java 8这个版本提供了很多实用的新特性,针对接口推出了接口默认方法，接口静态方法以及函数式接口，同时为了简化代码编写，推出了lambda表达式，为了增强对数据的操作，还定义了Stream操作等。这个版本目前是市场上一个应用最广泛，也是最重要的一个版本。

#	JDK8接口新特性
##	概述
JDK8中对接口规范进行了新的定义，允许在接口中定义默认方法（使用default关键字修饰），静态方法，同时还推出了函数式接口（使用@FunctionInterface注解描述）设计。
##	应用场景
基于JDK8中接口新规范的定义，不仅可以扩展接口新功能（新的标准），还能保持接口向前兼容的特性。例如Java API中的集合操作规范。

##	快速入门分析

> default方法设计及实现

JDK8中为了对接口业务进行扩展，但又不影响实现类，提供了默认方法。此类型的方法实用default关键字修饰，可以有方法体的实现。例如list接口中的sort方法：


```
 default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }

```

说明：一个接口中可以有多个默认方法，在实现类中可以有选择的对方法进行重写。例如：

```java
interface IA{
	default void doMethod01() {
		System.out.println("doMethod01");
	}
	default void doMethod02() {
		System.out.println("doMethod02");
	}
} 
class ClassA implements IA{
}
```


> 	static方法设计及实现

JDK8的接口中还支持静态方法，例如：

```java
interface IB{
	static void doMethod() {
		System.out.println("doMethod()");
	}
}
```



> 函数式接口设计及实现

Java8引入了一个是函数式接口（Functional Interfaces），此接口使用
@FunctionalInterface修饰，并且此接口内部只能包含一个抽象方法。


```java
@FunctionalInterface
public interface Comparator<T> {
   int compare(T o1, T o2);  // public abstract
}
```

说明：函数式接口推出的目的主要是为了配合后续Lambda表达式的应用。

##	应用案例增强分析及实现
在JDK中的java.util.function包中定义了大量函数式接口。常用的有如下几个：



> 消费型接口。

```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```


> 函数式接口。

```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```



> 	判定式接口。

```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
```



> 供给式接口。

```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

# JDK8中Lambda 表达式应用
##	概述
Java中的Lambda表达式是JDK8中的一种新特性，它允许我们将一段代码（这段代码可以理解为一个接口的实现）当成参数传递给某个方法，然后进行业务处理，这种方式更像是一种函数式编程风格，可以让代码结构更简洁，开发效率更高。
3.2	应用场景
Java中的Lambda为JAVA编程注入了函数式编程思想，在迭代操作，映射操作，聚合操作等很多方面的实现上做出了很大努力。并从语法角度简化了程序员对特定代码的编写，通过底层的类型推断，方法引用等特性让代码表现的更加优雅。现在已成为我们编程过程中常用的一种编程方式。
##	快速入门分析
最简单的Lambda表达式可由逗号分隔的参数列表、->符号和语句块组成，例如：


```

Arrays.asList( "a", "b", "d" )
		      .forEach( e -> System.out.println( e ) );

```

在上面这个代码中的参数e的类型是由编译器推理得出的，你也可以显式指定该参数的类型，例如：

```
Arrays.asList( "a", "b", "d" )
		      .forEach( ( String e ) -> System.out.println( e ) );
```


如果Lambda表达式需要更复杂的语句块，则可以使用花括号将该语句块括起来，类似于Java中的函数体，例如：

```
Arrays.asList( "a", "b", "d" ).forEach( e -> {
	    System.out.print( e );
	    System.out.println( );
} );
```


lambda 表达式可以让代码编写更加简洁。我们先来思考下普通的函数或方法具备的几个元素：
*	访问修饰符。
*	返回值类型。
*	方法名。
*	参数列表。
*	代码块。
在lambda 表达式应用过程中，你应该也注意到了，一般只有两个元素：

```
(parameter list) -> body
```
其中“->” 将参数列表与函数主体分离，旨在对给定参数进行处理。函数的主体可能是一条或多条语句。例如其常见结构如下：

```
() -> statement
```

```
 arg -> statement 
```

```
 (arg1, arg2, ...) -> {  body-block }
```

```
 (Type1 arg1, Type2 arg2, ...) -> { 
method-body-block;return value; 
}
```

Lambda表达式有返回值，返回值的类型也由编译器推理得出。如果Lambda表达式中的语句块只有一行，则可以不用使用return语句，下列两个代码片段效果相同：


```
Arrays.asList( "a", "b", "d" )
		   .sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
```

```
Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
		    int result = e1.compareTo( e2 );
		    return result;
		} );
```


##	应用案例增强实现
结合对lambda表达式基本语法的认识，通过如下案例在对lambda表达式继续强化分析和实现。
案例1：构建一个线程对象，执行Runnable类型的任务。
传统方式的实现，其关键代码如下：

```
new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("hello");
			}
	}).start();
```

基于JDK8中的Lambda表达式实现方式，对传统方式线程对象的创建进行简化，其关键代码如下：

```
new Thread(()->{
	System.out.println("hello");
}).start();
```

案例2：通过lambda表达式演示排序过程中代码的简化。

定义一字符串数组，然后对字符串数组中的内容，按字符串元素的长度对其进行排序。代码如下：

```
String[] strArray= {"abcd","ab","abc"};	
```

在JDK8之前传统的实现方案，基于Arrays类对数组中的元素进行排序操作，关
键代码实现如下：

```
Arrays.sort(strArray,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}
	});
```

基于JDK8中的Lambda表达式，对排序如上排序方案的代码实现过程进行简化
，关键代码如下：

```
Arrays.sort(strArray, (s1, s2) -> s1.length() - s2.length());
```


#	JDK8中方法引用
##	概述
方法引用是用来直接引用类方法、实例方法或者构造方法的一种新的方式。这里要特别强调一点的是“方法引用”提供的是一种对方法的引用而不是执行方法的方式，简单点理解的话就是可以将方法作为参数进行传递，我们还可以将方法引用理解为lambda的一种深层表达。
##	应用场景
方法引用是一种更简洁易懂的Lambda表达式，操作符是双冒号"::"，也可以将方法引用看成是一个更加紧凑，易读的Lambda表达式。
##	快速入门分析
方法引用是一种更简洁易懂的Lambda表达式，操作符是双冒号"::"，也可以将方法引用定义一个list集合，然后基于Lambda表达式迭代集合中的内容进行输出，关键代码如下：


```
List<String> list = Arrays.asList("a","b","c");
```

```
list.forEach(str -> System.out.println(str));
```


基于方法引用的方式，输出list集合中的具体内容的，然后与传统Lambda表达式方式，进行对比分析，关键代码如下：

```
list.forEach(System.out::println);
```


说明：当你要访问的接口方法与执行的方法引用参数相同，返回值也相同即可直接使用方法引用。
##	应用案例增强分析
JDK8方法的引用可分为如下几类：

> 构造方法引用。

格式：ClassName::new，应用默认构造函数。


```java
package com.cy.java8.methodref;
import java.util.function.Supplier;
public class TestConstructorMethodRef01 {
	public static void main(String[] args) {
		//1.传统方式
		Supplier<Object> s1=new Supplier<Object>() {
			@Override
			public Object get() {
				return new Object();
			}
		};
		System.out.println(s1.get());
		//2.Lambda方式
		Supplier<Object> s2=()->new Object();
		System.out.println(s2.get());
		
		//3.构造方法引用"类名::new"
		Supplier<Object> obj=Object::new;
		System.out.println(obj.get());
	}
}
```

> 	类静态方法引用。

格式：ClassName::static_method。


```
package com.cy.java8.methodref;
import java.util.function.Function;
public class TestClassMethodRef01 {

	public static void main(String[] args) {
		//1.传统应用方式
		Function<String, Integer> f1=
            new Function<String, Integer>() {
			@Override
			public Integer apply(String t) {
				return Integer.parseInt(t);
			}
		};
		Integer result=f1.apply("100");
		System.out.println(result);
		//2.lambda应用方式
		Function<String, Integer> f2=(t)->Integer.parseInt(t);
		System.out.println(f2.apply("200"));
		//3.类方法引用应用方式"类名::方法名"
		Function<String,Integer> f3=Integer::parseInt;
		System.out.println(f3.apply("300"));
	}
}
```


练习：比较两个整数大小。


```
Comparator<Integer> com=Integer::compare;

System.out.println(com.compare(39, 20));
```


> 类实例方法引用。

格式：ClassName::method，方法不能带参数。


```java
package com.cy.java8.methodref;
import java.io.File;
import java.util.function.Function;
public class TestClassInstanceMethodRef01 {

	public static void main(String[] args) {
		//1.传统方式
		Function<File,String> f1=new Function<File,String>() {
			@Override
			public String apply(File f) {
				return f.getAbsolutePath();
			}
		};
		System.out.println(f1.apply(new File("pom.xml")));
		//2.Lambda方式
		Function<File,String> f2=file->file.getAbsolutePath();
		System.out.println(f2.apply(new File("pom.xml")));
		
		//3.类实例方法引用"类名::实例方法名"
		Function<File,String> f3=File::getAbsolutePath;
		System.out.println(f3.apply(new File("pom.xml")));
		
	}
}
```


练习：堆数组中内容进行排序，通过方法引用简化编写。


```
Arrays.sort(strArray,(s1,s2)->s1.compareToIgnoreCase(s2));
Arrays.sort(strArray, String::compareToIgnoreCase);
```



> 对象实例方法引用。

格式：对象实例::method，方法不能带参数。

```
public class TestObjectInstanceMethodRef01 {

	public static void main(String[] args) {
		List<String> list=Arrays.asList("A","B","C");
		//传统方式
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});
		//Lambda表达式方式
		list.forEach(t->System.out.println(t));
		//方法引用方式
		PrintStream ps=System.out;
		list.forEach(ps::println);
		list.forEach(System.out::println);
	}
}
```


练习:获取集合中元素的个数。


```
List<Integer> list=Arrays.asList(10,20);
Supplier<Integer> supplier=(list::size);
System.out.println(supplier.get());
```



# JDK8中Stream API应用
##	概述
Stream 作为 Java 8 的一大亮点，它与 java.io 包里的 InputStream 和 OutputStream 是完全不同的概念。Java 8 中的 Stream 是对集合（Collection）对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作，或者大批量数据操作。
##	应用场景
在当今这个数据爆炸的时代，数据来源多样化、数据海量化，很多时候不得不脱离 RDBMS，以底层返回的数据为基础进行更上层的数据统计。而原有 Java 的集合 API 中，仅仅有极少量的辅助型方法，更多的时候是程序员需要用 Iterator 来遍历集合，然后完成相关的聚合应用逻辑。这是一种远不够高效而且相对比较笨拙的方法。在JDK8中使用 Stream 对象，不仅丰富了在业务层面对数据处理的方式，还可以让代码更加简洁、易读和高效。

## 快速入门分析
我们在使用Stream对象时，一般会按照如下为三个步骤进行操作：
第一步：创建Stream流对象。
第二步：Stream流中间操作。
第三步：Stream流终止操作。
Stream对象的操作过程，可通过下图进行进一步分析。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210708192049974.png)
Steam对象简易应用，代码如下：

```
List<Integer> list = Arrays.asList(3, 2, 12, 5, 6, 11, 13);
long count = list.stream()
                 .filter(i -> i % 2 == 0)
                 .count();
System.out.println(count);
```


##	应用案例增强分析

> Stream对象创建。

Stream对象的创建，常见方式有如下几种：
1)	借助Collection接口中的stream()或parallelStream（）方法。
2)	借助Arrays类中的stream(...)方法。
3)	借助Stream类中的of(...)方法。
4)	借助Stream类中的iterator和generator方法（无限操作）。

Stream对象创建，案例分析如下：

```
Collection<Integer> col=new ArrayList<>();
      ...
	Stream<Integer> s1=col.stream();
	Stream<Integer> s2=col.parallelStream();
		
	IntStream s3=Arrays.stream(new int[] {1,2,3,4});
	Stream<Integer> s4=Stream.of(10,20,30,40);
		
	Stream<Integer> s5=Stream.iterate(2, (x)->x+2);
	s5.forEach(System.out::println);
		
	Stream<Double> s6=Stream.generate(()->Math.random());
	s6.forEach(System.out::println);
```

> Stream中间操作

Stream 对象创建以后可以基于业务执行一些中间操作，但这些操作的结果需要借助终止操作进行输出，案例分析如下：
初始条件：给定list集合作为Stream操作的对象，代码如下：

```
List<Integer> list=Arrays.asList(100,101,102,200);
```


对数据进行过滤：

```
//输出集合中所有的偶数
//1.创建流
Stream<Integer> s1=list.stream();
//2.中间操作(过滤)
Stream<Integer> s2=s1.filter((n)->n%2==0);
//3.终止操作
s2.forEach(System.out::println);
//也可以将多个操作合在一起
list.stream().filter(n->n%2==0).forEach(System.out::println);
```


限定操作(limit):

```
list.stream()
.filter(n->n%2==0)
.limit(2)
.forEach(System.out::println);
```


跳过操作(skip)：

```
list.stream()
.filter(n->n%2==0)
.skip(2)
.forEach(System.out::println);
```


去重操作(distinct)：

```
list.stream()
.distinct()
.forEach(System.out::println);
```

排序操作(sorted)：底层基于内部比较器Comparable或外部Comparator比较器进行比对。

```
list.stream()
.sorted()
.forEach(System.out::println);
```


```
list.stream()
.sorted((s1,s2)->{//Comparator
				return s1-s2;
	    }).forEach(System.out::println);
```


映射操作(map)：

```
List<String> list=Arrays.asList("a","bc","def");

list.stream()
.map((x)->x.toUpperCase())
.forEach(System.out::println);

list.stream()
.map((x)->x.length())
.forEach(System.out::println);
```



> Stream终止操作。

Stream终止操作是Stream的结束操作，案例分析如下：

```
List<String> list=Arrays.asList("a","bc","def");
list.stream()
.map((x)->x.toUpperCase())
.forEach(System.out::println);
```

```
list.stream()
.map((x)->x.length())
.forEach(System.out::println);
```


案例：初始条件定义，给定一个list集合：

```
List<Integer> list=Arrays.asList(10,11,12,13,14,15);
```


match操作：

```
boolean flag=list.stream().allMatch((x)->x%2==0);
System.out.println(flag);
flag=list.stream().anyMatch((x)->x%2==0);
System.out.println(flag);
flag=list.stream().noneMatch((x)->x>20);
System.out.println(flag);
```


find操作：

```
Optional<Integer> optional=list.stream().sorted().findFirst();
System.out.println(optional.get());
optional=list.parallelStream().filter((x)->x%2!=0).findAny();
System.out.println(optional.get());
```


count操作：

```
long count=list.stream().count();
System.out.println(count);
```


求最大，最小值：

```
optional=list.stream().max((x,y)->{return x-y;});
System.out.println(optional.get());
optional=list.stream().min((x,y)->{return x-y;});
System.out.println(optional.get());
```


forEach迭代操作：

```
list.stream().forEach(System.out::println);
```



Reduce(规约)操作：

//计算集合中所有元素的和，其中第一个参数0为初始值，然后与后面每个值累加

```
Integer sum=list.stream().reduce(0,(x,y)->{return x+y;});
System.out.println(sum);
```


Collector(收集)操作：

```
List<Integer> result=
list.stream()
.map(x->x*2)
.collect(Collectors.toList());
System.out.println(result);

list.stream().map(x->x*2).collect(Collectors.toSet());
System.out.println(result);

double avg=
list.stream()
.collect(Collectors.averagingDouble(x->x));
System.out.println(avg);

Optional<Integer> max=
list.stream().collect(Collectors.maxBy((x,y)->{return x-y;}));
System.out.println(max.get());

Map<Object,List<Integer>> map=
list.stream().collect(Collectors.groupingBy(x->x%2==0));
System.out.println(map);
```

# JDK8中Stream 课堂练习
##	reduce操作
案例1：计算多个整数的和。

```
static void doTestReduce01() {
		 List<Integer> list = Arrays.asList(1,2,3,4,5,6);
		 Optional<Integer> count = 
              list.stream().reduce((a, b) -> (a + b));
		 System.out.println(count.get()); // 21
	}
```


案例2：计算多个整数乘积，然后再乘以2。

```
static void doTestReduce02() {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6);
		Integer count = list.stream().reduce(2, (a, b) -> (a * b));
		System.out.println(count);  // 1440
	}
```

案例3：计算多个整数的和，假如超出范围，则对其进行类型转换。.
案例1和2的缺点在于返回的数据都只能和 Stream 流中元素类型一致，但假如求和或乘积之后的数值超过了 Integer 能够表示的范围怎么办？例如，需要使用 Long 类型接收，这就用到了我们下面reduce() 方法的应用形式了。

```
static void doTestReduce03() {
		List<Integer> list = 
 Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE);
		long count = 
  list.stream().reduce(0L, (a, b) -> (a + b), (a,b) -> 0L);
		System.out.println(count);
}
```


##	collect操作实现
获取指定目录下所有目录文件的文件名。

```
List<String> allDirNames =
    Arrays.stream(new File("d:\\")
          .listFiles())
          .filter(File::isDirectory)
          .map(File::getName)
          .collect(Collectors.toList());
System.out.println(allDirNames);
```


##	并行流的简易应用
并行流应用的目的主要借助多核处理器优势，提高的操作性能。


```
 public static void main (String[] args) {
        String[] strings =  {"1", "2", "3", "4", "5"};
        doPrint(Arrays.stream(strings).sequential());
        doPrint(Arrays.stream(strings).parallel());
    }
    public static void doPrint (Stream<String> stream) {
        stream.forEach(s -> {
            System.out.println(
            Thread.currentThread().getName()+"->"+s);
        });
    }
```



#	JDK8中新日期对象应用
##	概述
在Java8之前，日期和时间的管理一直是令Java开发者很痛苦的一个的问题。java.util.Date、java.util.Calendar，SimpleDateFormat都一直没有很好解决这个问题。故此，Java8引入了一套全新的日期时间处理API，新的API基于ISO标准日历系统，解决了以前日期和时间类的很多弊端问题。

##	应用场景
Java8中的时间处理API定义在java.time包中，这些API具备不可变且线程安全特性,具备准确和灵活特性。所以现在基本可以使用这组API替换所有原有历史版本中时间API的应用。

##	快速入门分析
新日器对象类型中，常用API应用分析:

> Instant 时间戳对象应用，默认是0时区，比北京少8个时区。

```
//获取瞬时对象(当前时间年月日时分秒),Instant是绝对时间，没有时区的概念
Instant instant1 ==Instant.now();//Clock.systemUTC().instant();
System.out.println(instant1);
//通过这种方式获取的时间戳与北京时间相差8个时区，需要修正为北京时间
instant1=instant1.plusMillis(TimeUnit.HOURS.toMillis(8));;
//输出系统可用时区
System.out.println(ZoneId.getAvailableZoneIds());
//输出系统默认时区
System.out.println(ZoneId.systemDefault());
//输出系统默认时区时间
System.out.println(Instant.now().atZone(ZoneId.systemDefault()));

获取时间间隔。

Instant start = Instant.now();
...
 Instant end = Instant.now();
 Duration timeElapsed = Duration.between(start, end);
 System.out.println("Milliseconds: " + timeElapsed.toMillis());
```





> LocalDate 日期对象，不包含具体时间。


```
LocalDate ld1=LocalDate.now();
System.out.println(ld1);
LocalDate ld2 = LocalDate.of(2019, Month.JANUARY, 8);
System.out.println(ld2);
LocalDate ld3=LocalDate.parse("2019-12-12");
System.out.println(ld3);
```


> LocalTime 时间对象，不包含日期。


```
LocalTime lt1=LocalTime.now();
System.out.println(lt1);
LocalTime lt2=LocalTime.now(ZoneId.systemDefault());
System.out.println(lt2);
long t=ChronoUnit.HOURS.between(lt1, lt2);
System.out.println(t);
```



> LocalDateTime  包含了日期和时间对象，没有时区信息。

```
LocalDateTime ldt02 = 
LocalDateTime.of(2019, Month.DECEMBER, 31, 23, 59, 59);
System.out.println(ldt02);//2019-12-31T23:59:59
		
DayOfWeek dayOfWeek = ldt02.getDayOfWeek();
System.out.println(dayOfWeek);      // WEDNESDAY
```


> ZoneDateTime  包含时区的完整日期时间对象，偏移量以UTC时间为基准。

```
ZonedDateTime zdt01=ZonedDateTime.now();
System.out.println(zdt01);
ZoneId zd01=TimeZone.getDefault().toZoneId();
System.out.println(zd01);
```


##	应用案例增强分析
项目中我们经常会用到日期类型转换，在JDK8中的实现方式如下：

```
LocalDateTime ld4 = 
LocalDateTime.parse("2019/12/12 12:12:12",
DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
System.out.println(ld4);
```


说明：jdk8之前的日期与字符串之间的转换通常会借助SimpleDateFormat对象，但是此对象线程不安全，通常要借助ThreadLocal对象，保证SimpleDateFormat对象每个线程一份。

#	JDK8新特性总结
##	重难点分析
*	接口新特性?(默认方法，函数式接口，静态方法)
*	Lambda表达式?(函数式，简化语法，开发效率)
*	方法引用(MethodReference)
*	Stream应用实践分析?（数据操作更方便，创建，中间操作，终止操作）
*	常用新的日期对象应用?（灵活，安全，准确）

##	FAQ分析
*	常用的函数式接口有哪些？(消费型，供给型，判定型，函数式)
*	Lambda表达式关键作用是什么？(简洁，开发效率)
*	Stream应用中有哪些常用操作？（过滤，统计，映射，规约，收集,...）
*	JDK8中常用的日期API有哪些？(Instant,LocalDateTime,DateTimeFormater)