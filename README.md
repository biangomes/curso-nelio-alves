# Java 2022 Completo: Do Zero ao Profissional + Projetos!



### Aula 99. Construtor #01

**Construtores** são métodos especiais que criam novos objetos a partir de uma classe, como se fosse um portal entre uma classe e um construtor. É uma operação especial da classe, que é executada no momento da instanciação do objeto.

Usos comuns:

- Iniciar valores dos atributos;
- Permitir ou obrigar que o objeto receba dados/dependências no momento de sua instanciação (**injeção de dependência**).

**Construtor padrão:** o construtor que **não** recebe **nenhum** parâmetro. Segue o exemplo abaixo:

```java
Produto p1 = new Produto();		// nao recebe nenhum parametro, é o construtor padrão
```

O construtor do exemplo acima é o `Produto()` do lado direito da igualdade. O `Produto` do lado esquerdo é a **classe**.

De acordo com a [Unicamp](https://www.dca.fee.unicamp.br/cursos/PooJava/metodos/construtor.html]), **toda classe tem pelo menos um construtor sempre definido**. Ou seja, caso nenhum construtor seja definido explicitamente pelo programador, a classe disponibiliza um **construtor padrão**. Ele é unicamente invocado no momento da criação do objeto através do operador `new`. É plenamente possível mais de um construtor para a mesma classe. Vejamos os construtores da classe `Point`:

> A classe `java.awt.Point` tem dois campos, x e y, do tipo `int`. Essa classe oferece um construtor que permite criar um ponto representando as coordenadas do ponto, passadas como argumentos para o construtor.

```java
public Point(int x, int y)
    
    Constructs and initializes a point at the specified (x, y) location in the coordinate space.
    
    Parameters:
		x - the x coordinate
        y - the y coordinate
```

> Há outro construtor que recebe como argumento um outro objeto da classe `Point`.

```java
public Point(Point p)
    
    Constructs and initializes a point with the same location as the specified Point object.
    
    Parameters:
		p - a point.
            
    Since:
		JDK 1.1
```

> E, por fim, o construtor padrão.

```java
public Point()
    
    Construct and initializes a point at the origin (0,0) of the coordinate space.
    
    Since:
		JDK 1.1
```

Suponhamos, agora, a classe `Produto`:

```java
class Produto() {
	Produto(int a) {
        // Construtor explícito
    }
}
```

Perceba que não é colocado o tipo de retorno antes do construtor, pois invalidaria a sua definição. **A diferença entre método e construtor é que o primeiro possui tipo de retorno** (`void`, `int` etc). 

**A aparição do construtor explícito elimina o construtor padrão**.

```java
Produto p1 = new Produto(3);
```

No conteúdo de herança e polimorfismo, quando criamos uma classe filha, ou seja, uma classe que herda todos os atributos e métodos de uma super classe; precisamos criar o nosso próprio construtor.

Vamos ver um exemplo.

`Conta.java`

```java

public class Conta {
	
	private double saldo;
	private int agencia;
	private int numero;
	private Cliente titular;
	private static int total = 0;
	
	
	// Criando um construtor
	public Conta(int agencia, int numero) {
		Conta.total++;
		System.out.println("O total de contas é: " + Conta.total + "\n##########");
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = 100.0;
		System.out.println("ID " + Conta.total + "\nNúmero: " + this.numero + "\nAgência: " + this.agencia + "\n##########");
	}
	
	public void deposita(double valor) {
		this.saldo += valor;
	}
	
	public boolean saca(double valor) {
		if(this.saldo >= valor) {
			this.saldo -= valor;
			return true;
		}
		else {
			this.saldo -= valor;
			System.out.println("Está no cheque especial.");
			System.out.println("R$ " + this.saldo);
			return false;
		}
	}
	
	public boolean transfere(double valor, Conta destino) {
		if(this.saca(valor)) {
			destino.deposita(valor);
			System.out.println("Saldo total R$ " + this.saldo);
			return true;
		}
		else {
			System.out.println("Valor indisponível. - R$ " + this.saldo);
			return false;
		}
	}
	
	// Getters
	public double getSaldo() {
		return this.saldo;
	}
	
	public int getAgencia() {
		return this.agencia;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public Cliente getTitular() {
		return this.titular;
	}
	
	
	// Setters
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void setAgencia(int agencia) {
		if(this.agencia<=0) {
			System.out.println("Não pode agencia menor ou igual a zero.");
		} else {
			this.agencia = agencia;
		}
	}
	
	public void setNumero(int numero) {
		if(this.numero<=0) {
			System.out.println("Não pode número menor ou igual a zero.");
		} else {
			this.numero = numero;
		}
	}
	
	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

}

```

Veja que na classe acima, além do construtor padrão, temos o construtor:

```java
// Criando um construtor
	public Conta(int agencia, int numero) {
		Conta.total++;
		System.out.println("O total de contas é: " + Conta.total + "\n##########");
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = 100.0;
		System.out.println("ID " + Conta.total + "\nNúmero: " + this.numero + "\nAgência: " + this.agencia + "\n##########");
	}
```

Quando criamos uma classe que herdará os atributos e comportamentos da classe `Conta`, chamada `ContaCorrente` e `ContaPoupança`(posteriormente), temos o seguinte:

```java
public class ContaCorrente extends Conta {

	// O construtor da classe mãe só pertence a ela própria. Assim, precisamos construir o nosso próprio construtor.
}

```

O código acima irá gerar um erro, em decorrência de algo mencionado mais acima.

> (...) quando criamos uma classe filha, ou seja, uma classe que herda todos os atributos e métodos de uma super classe; precisamos criar o nosso próprio construtor.

Portanto, quando fizermos o código abaixo não será mais reclamado nenhum erro.

```java
// new ContaCorrente()
public class ContaCorrente extends Conta {

	// O construtor da classe mãe só pertence a ela própria. Assim, precisamos construir o nosso próprio construtor.
	public ContaCorrente(int agencia, int numero) {
		super(agencia, numero);
	}
}

```

A palavra `super` pede para o Java subir até a classe mãe e "trazer" o que tem lá para a classe filha.

Em suma:

> **A classe filha não herda os construtores da classe mãe, por isso é necessário EXPLICITAR a herança dos construtores.**

**Sobrecarga:** quando uma classe possui mais de um construtor, além do padrão. De acordo com [programiz](https://www.programiz.com/java-programming/method-overloading#:~:text=In%20Java%2C%20two%20or%20more,feature%20is%20called%20method%20overloading.), dois ou mais métodos podem ter o **mesmo nome** desde que tenham diferentes **números de parâmetros**, **diferentes tipos de parâmetros** ou **os dois**. Veja o exemplo abaixo:

```java
class HelperService() {
    private String formatNumber(int value) {
        return String.format("%d", value);
    }
    
    private String formatNumber(double value) {
        return String.format("%.3f", value);
    }
    
    private String formatNumber(String value) {
        return String.format("%.2f", Double.parseDouble(value));
    }
    
    public static void main(String[] args) {
        HelperService hs = new HelperService();
        System.out.println(hs.formatNumber(500));
        System.out.println(hs.formatNumber(89.9934));
        System.out.println(hs.formatNumber("550"));
    }
}
```

Veja a saída do código acima:

![](C:\Users\beatriz\Pictures\Screenpresso\HelperService exit.png)

### Aula 120. Array #01

Array é uma estrutura **estática**, possui tamanho **fixo**. É possível criar um array de **6 posições** e outro array de mais posições e **associar** uma variável do primeiro array com o segundo. O array é **homogêneo**, ou seja, só aceita o **mesmo tipo de dado**.

A declaração de array é:

```java
int[] a = new Array[3]		// criando um array inteiro de 3 posições
```

Podemos **inserir** informações dentro do Array acessando diretamente pelo **índice**, como abaixo.

```java
a[0] = 1;
a[1] = 2;
a[3] = 3;
```

É possível também através de **loop**. Vamos armazenar no array `a`, o dobro do índice, i.e, índice 0 multiplicado por 2 é igual a 0, índice 1 multiplicado por 2 é igual a 2, portanto, a[1] = 2.

```java
for(int i=0; i<a.length; i++) {
    a[i] = 2 * i; 
}
```

Para **exibir** na tela o conteúdo do array, podemos fazer por meio de loop.

```java
for(int i=0; i<a.length; i++) {
    System.out.println(a[i])
}
```

Assim como utilizando a class `Arrays`.

```java
import java.utils.Arrays;
// ...
System.out.println(Arrays.toString(a));
```



### Aula 126. Entendendo Equals e Hashcode

**Data:** 11-05-2022.

```java
Produto p1 = new Produto();
Produto p2 = new Produto();

// Iniciando
p1.nome = "Alvejante";
p2.nome = "Alvejante";   
```

Dado o código acima, podemos assumir que `p1 == p2` é `True`?

**R.:** Não, pois a comparação entre objetos vai no nível da memória. O endereço de memória do objeto `p1` é diferente do endereço de memória o objeto `p2`.

Agora consideremos o código acima com uma alteração.

```java
Produto p1 = new Produto();
Produto p2 = new Produto();

// Iniciando
p1.nome = "Sabão";
p2.nome = "Sabão";

// ?
p1.equals(p2);
```

Por mais que se tenha empregado um método chamado `equals`, ainda assim o resultado dessa expressão lógica será `False`.

De acordo com [DevMedia](https://www.devmedia.com.br/sobrescrevendo-o-metodo-equals-em-java/26484#:~:text=O%20método%20equals%20é%20utilizado,conteúdo%2C%20possam%20ser%20considerados%20iguais.):

> O método `equals` é utilizado para comparações. A classe `String` e as classes `Wrapper` sobrescrevem `equals()` para garantir que dois objetos desses tipos, com o mesmo conteúdo, possam ser considerados iguais. Quando a finalidade for descobrir se duas referências são iguais, o operador `==` deverá ser usado, pois serão comparados os bits das variáveis.

De acordo com o blog [cod3r](https://blog.cod3r.com.br/desmistificando-hashcode-e-equals-em-java/), existem alguns princípios definidos pelo próprio Java EE acerca da implementação do equals. São eles:

- **Consistente:** para quaisquer objetos `x` e `y`, o valor de `x.equals(y)` só poderá ser diferente se as propriedades em `equals()` mudem;
- **Transitivo:** para quaisquer objetos `x` e `y`, se `x.equals(y)` retorna `true` e `y.equals(z)` retorna `true`, então `x.equals(z)` deve retornar `true`;
- **Simétrico:** dado dois objetos `x` e `y`, `x.equals(y)` deve retornar `true` se, e somente se, `y.equals(x)` também for `true`.
- **Reflexivo:** um objeto `x` deve ser igual a ele mesmo, ou seja, retornar `true` em `x.equals(x)`.

### Por dentro de hashCode

O hashCode é uma ferramenta da JVM usada para montar a tabela hash de modo correto. A tabela hash (Tabela de Dispersão ou Tabela de Espelhamento) é uma tabela onde as informações são armazenadas conforme uma **hash**, que é calculado se baseando nas propriedades da informação.

**Exemplo:** temos uma tabela com informações de todos os pacientes de um hospital. Se fossemos buscar um paciente em específico, demoraria um tempo 
$$
(O(n))
$$
em uma busca **linear**. Ou
$$
(O(log{N}))
$$
para buscas **binárias**.

Utilizando uma tabela **hash**, a busca reduz seu tempo de busca para 
$$
(O(1))
$$
para qualquer situação.

Em resumo:

> - Utilizar `==` : compara **memória**;
> - Utilizar `equals` : compara **memória**;
> - Utilizar `hashCode` : compara por um **critério**.

#### Códigos da aula

**Estudos\\...\udemy.aula127_equals\Equals.java**

```java
package udemy.aula127_equals;

import java.util.Date;

public class Equals {
    public static void main(String[] args) {

        Usuario u1 = new Usuario();
        Usuario u2 = new Usuario();

        u1.nome = "Ana Silva";
        u1.email = "anafcarvalhos@gmail.com";
        u2.nome = "Ana Silva";
        u2.email = "anafcarvalhos@gmail.com";

        System.out.println(u1 == u2);       // false
        System.out.println(u1.equals(u2));      // false
        System.out.println(u2.equals(u1));      // false
        System.out.println(u2.equals(new Date()));

        /*
        System.out.println(u2.equals(new Date()));

            Exception in thread "main" java.lang.ClassCastException: class java.util.Date cannot be cast to class udemy.aula127_equals.Usuario (java.util.Date is in module java.base of loader 'bootstrap'; udemy.aula127_equals.Usuario is in unnamed module of loader 'app')
            at udemy.aula127_equals.Usuario.equals(Usuario.java:10)
            at udemy.aula127_equals.EqualsHashcode.main(EqualsHashcode.java:20)

         */
        //System.out.println(u1.equals(u1));    true
        //System.out.println(u2.equals(u2));    true
    }
}

```

**Estudos\\...\udemy.aula127_equals\Usuario.java**

```java
package udemy.aula127_equals;

public class Usuario {
    String nome;
    String email;

    @Override
    public boolean equals(Object objeto) {

        // Resolvendo a Exception levantada pelo comentário do Eqyals.java
        if (objeto instanceof Usuario) {

            Usuario outro = (Usuario) objeto;

            boolean nomeIgual = outro.nome.equals(this.nome);
            boolean emailIgual = outro.email.equals(this.email);

            return nomeIgual && emailIgual;
        }
        else {
            return false;
        }
        //return super.equals(objeto);
    }

    // O hashcode será abordado em outra aula!

    public int hashCode() {
        return 0;
    }
}

```

---

### Aula 128. Collections

Diferentemente dos Arrays, as Collections **não** possuem tamanhos **fixos**, isto é, ***o tamanho dela é variável, é mutável***. Outra característica das Collections é que ela é **heterogênea** e **não** comporta **tipos primitivos**, ou seja, não é possível guardar um int, double etc dentro de uma Collection.

​																													**COLLECTIONS**

| Set                       | List             | Map                        | Queue                     | Stack                    |
| ------------------------- | ---------------- | -------------------------- | ------------------------- | ------------------------ |
| Não ordenado (por padrão) | Indexada         | Chave/valor                | Implementa fila           | Implementa pilha (stack) |
| Não indexado              | Aceita repetição | Chave não aceita repetição | First In/First Out (fifo) | Last In/First Out (lifo) |
| Não aceita repetição      |                  |                            |                           |                          |

### Aula 129. Set #01

**Set** pode ser homogêneo ou heterogêneo, não aceita objetos duplicados, pode ser ordenado. 

`HashSet` é uma das possibilidades de se trabalhar com conjuntos dentro do Java. Ele é uma interface e define as regras que terão dentro de um set.

Conceitualmente, `HashSet` é uma **classe** que implementa a **interface `Set`**, apoiada por uma tabela hash que é, na verdade, uma instância `HashMap`. 

**Estudos\\...\udemy.aula129_set01\ConjuntoBaguncado.java**

```java
package udemy.aula129_set01;

import java.util.HashSet;
import java.util.Set;

public class ConjuntoBaguncado {
    public static void main(String[] args) {

        HashSet conjunto = new HashSet();


        /*Lembrando que a interface Set (implementada aqui pela classe HashSet) não aceita tipos primitivos
        Porém, quando utilizamos o método add(), o parâmetro dele será instanciado pela classe do tipo primitivo.
        Este é o conceito de wrapper.
        Obs.: o conceito acima pode estar equivocado, pois ainda não vi wrapper, apenas o que é.*/
        conjunto.add(1.2);  // double   -> Double
        conjunto.add(true); // boolean  -> Boolean
        conjunto.add("Beatriz");    // string   -> Nao precisa converter pois ja eh objeto
        conjunto.add(1);    // int  -> Integer
        conjunto.add('x');  // char -> Caracter


        // Saber o tamanho do conjunto
        System.out.println("O tamanho é: " + conjunto.size());

        conjunto.add(1);    // continua com tamanho = 5, pq eh um elemento repetio


        // Removendo um elemento
        System.out.println(conjunto.remove("Beatriz"));

        // Verificando se o elemento "Beatriz" está contido no conjunto
        System.out.println("O elemento ainda está no conjunto? R.: " + conjunto.contains("Beatriz"));
        System.out.println("O elemento ainda está no conjunto? R.: " + conjunto.contains(1.2));
        System.out.println("O novo tamanho é: " + conjunto.size());

        // Novo HashSet
        Set nums = new HashSet();
        nums.add(1);
        nums.add(2);
        nums.add(3);

        // Exibindo no console
        System.out.println(nums);
        System.out.println(conjunto);

        // Uniao entre conjunto e nums
        //conjunto.addAll(nums);

        // Intersecçao entre conjunto e nums
        conjunto.retainAll(nums);
        System.out.println("conjunto E nums = " + conjunto);

        // Limpa o conjunto
        conjunto.clear();
        System.out.println(conjunto);
    }
}
```



### Aula 130. Set #02

Vimos anteriormente que `Set` pode ser homogêneo ou heterogêneo (não é recomendável), assim como também foi visto a sintaxe "genérica". Para determinar que um Set será **homogêneo**, é utilizada a sintaxe abaixo:

```java
Set<String> lista = new HashSet<String>();
```

Ou seja, existe um set chamado `lista`, que aceita **apenas** `String`.

**Estudos\\...\udemy.aula129_set01\ConjuntoArrumado.java**

```java
package udemy.aula130_set02;

import java.util.HashSet;
import java.util.Set;

public class ConjuntoComportado {
    public static void main(String[] args) {

        Set<String> listaAprovados = new HashSet<String>();

        listaAprovados.add("Beatriz");
        listaAprovados.add("Ana");
        listaAprovados.add("Bea");
        listaAprovados.add("Aninha");

        for (String candidato: listaAprovados) {
            System.out.println(candidato);
        }
    }
}

```

O resultado do código acima imprime desordenadamente, ou seja, pode retornar `Bea`, `Aninha`, `Ana` e `Beatriz`, dentre as outras combinações. Para que seja armazenado com ordem, é utilizado a classe `TreeSet()`, ficando assim:

**Estudos\\...\udemy.aula130_set02\ConjuntoComportado.java**

``` java
package udemy.aula130_set02;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ConjuntoComportado {
    public static void main(String[] args) {

//      Set<String> listaAprovados = new HashSet<String>();
        SortedSet<String> listaAprovadosOrdenada = new TreeSet<String>();


//        listaAprovados.add("Beatriz");
//        listaAprovados.add("Ana");
//        listaAprovados.add("Bea");
//        listaAprovados.add("Aninha");
//
//        for (String candidato: listaAprovados) {
//            System.out.println(candidato);
//        }

        listaAprovadosOrdenada.add("Beatriz");
        listaAprovadosOrdenada.add("Cris");
        listaAprovadosOrdenada.add("Ana");

        for (String candidatoOrdenado: listaAprovadosOrdenada) {
            System.out.println(candidatoOrdenado);
        }
    }
}

```

O retorno do código acima será: `Ana`, `Cris` e `Beatriz`.

### Aula 131. List

As `list` podem ser heterogêneas, homogêneas, **aceita** objetos duplicados, é ordenada e é **indexada** (diferença em relação ao `Set`. É possível acessar os elementos através dos índices).

**Estudos\...\udemy.aula131_list\Usuario.java**

```java
package udemy.aula131_list;

public class Usuario {

    String nome;

    // Criando um construtor
    Usuario(String nome) {
        this.nome = nome;
    }

    // Método que retorna as informações contidas no objeto
    public String toString() {
        return "Meu nome é " + this.nome + ".";
    }


    // Definindo o hashCode
    @Override
    public int hashCode() {
        // Algoritmo para que seja gerado números mais diferentes possível
        final int prime = 31;
        int result = 1;
        result = prime * result + (((nome == null)) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;

    }
}

```

Algumas observações sobre o código acima:

- A sintaxe `Usuario(String nome){ (...) }` se trata de um **construtor**.
- O método público `toString` retorna uma representação mais legível para uma pessoa das informações do objeto. **Sem** o método é retornado algo do tipo: `Usuario@163b91`, que é o endereço de memória daquele objeto. **Com** este método é retornado (seguindo o exemplo acima): `Meu nome é Arthur.`. O método `toString` sempre é chamado automaticamente quando é utilizado um `println`, `printf`, etc.
- O método `hashCode()` deixa os números mais aleatórios possível.
- O método `equals`.

**Estudos\...\udemy.aula131_list\Lista.java**

```java
package udemy.aula131_list;

import java.util.ArrayList;

public class Lista {
    public static void main(String[] args) {

        ArrayList<Usuario> lista = new ArrayList<>();

        Usuario u1 = new Usuario("Bia");
        lista.add(new Usuario("Ana"));
        lista.add(new Usuario("Arthur"));
        lista.add(new Usuario("Lia"));
        lista.add(new Usuario("Manu"));


        // Acessando um elemento específico
        System.out.println(lista.get(3).nome);
        System.out.println(lista.get(1));
        System.out.println("\n");

        for (Usuario u: lista) {
            System.out.println(u.nome);
        }
    }
}

```

**Sobrescrevendo o método equals da classe `Usuario`**

```java
package udemy.aula131_list;

public class Usuario {

    String nome;

    // Criando um construtor
    Usuario(String nome) {
        this.nome = nome;
    }

    // Método que retorna as informações contidas no objeto
    public String toString() {
        return "Meu nome é " + this.nome + ".";
    }


    // Definindo o hashCode
    @Override
    public int hashCode() {
        // Algoritmo para que seja gerado números mais diferentes possível
        final int prime = 31;
        int result = 1;
        result = prime * result + (((nome == null)) ? 0 : nome.hashCode());
        return result;
    }


    // Definindo o equals
/*    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;*/

    // Sobreescrevendo o método equals, pois se trata de um caso mais simples
    @Override
    public boolean equals(Object obj) {
        Usuario outroUsuario = (Usuario) obj;
        return this.nome.equals(outroUsuario.nome);

    }
}

```

---

### Método `@Override`

O método `@Override` permite que uma subclasse ou classe filha crie uma nova implementação específica de um método já criado. Quando o método em uma subclasse possui o mesmo **nome**, **parâmetro** e **tipo de retorno** de um método em uma **super classe**, então o nome na subclasse recebe o método `@Override`, que significa literalmente sobrescrever. 

![](C:\Users\beatriz\Pictures\overriding-java.png)

Veja o exemplo abaixo:

```java
// Classe base
class Parent() {
    void show() {
        System.out.println("Parent's show()");
    }
}

// Classe herdada
class Child extends Parent {
    // O método abaixo sobrescreve o metodo show() da classe Parent
    @Override
    void show() {
        System.out.println("Child's show()");
    }
}

// Classe de teste
class Main {
    public static void main(String[] args) {
        // If a Parent type reference refers
        // to a Parent object, then Parent's
        // show is called
        Parent obj1 = new Parent();
        obj1.show();
  
        // If a Parent type reference refers
        // to a Child object Child's show()
        // is called. This is called RUN TIME
        // POLYMORPHISM.
        Parent obj2 = new Child();
        obj2.show();
    }
}
```

**Algumas regras para substituição de método**

1. Overriding e modificador de acesso: o modificador de acesso do método substituto deve permitir mais, não menos, que o modificador de acesso do método substituído.

Um método de instância `protected` na superclasse pode se tornar `public` na subclasse, mas não `private` na subclasse. Caso se torne `private` irá gerar um ***compile-time error***.

### Anedotas

Vimos até agora como imprimir para o usuário, porém ainda não vimos como receber as **entradas**. Para ler dados inputados do teclado, precisamos importar a biblioteca `Scanner`, da seguinte forma:

```java
import java.util.Scanner;

public class ImportandoScanner {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
    }
}
```

O código abaixo mostra dois tipos de inputs: `char` e `double`.

```java
import java.util.Scanner;

public class ConversaoTemperatura {
	public static void main(String[] args) {
		
		Scanner r = new Scanner(System.in);
		char resposta;
		
		do {
			System.out.print("Digite a temperatura em Celsius: ");
			Converte c = new Converte(r.nextDouble());
			System.out.print("Deseja repetir (s/n)?");
			resposta = r.next().charAt(0);
		} while (resposta != 'n');
		
		r.close();
	}

}

```

O código acima dará um erro do tipo `InputMismatchException` ao colocar um valor de `30.0` (pontos nos valores decimais no caso) para o objeto `c`. Assim, para não gerar esse erro, precisamos colocar `30,0`, por exemplo. Para resolver esse problema, devemos importar a biblioteca `Locale`, deixando o código abaixo:

```java
import java.util.Locale;

public class PontosDecimais {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
    }
}
```

### Membros estáticos

Também são chamados **membros de classe**, que são **opostos** aos membros de **instância**. Estes membros estáticos **não dependem de objetos**, são chamados a partir do próprio nome da classe. 

- **Aplicações comuns:**
  - Utiliários (calculadora, alarme etc): `Math.sqrt(double)`
  - Declaração de constantes.

Uma classe que possui somente membros estáticos pode ser uma classe estática também. Esta classe **não poderá** ser instanciada.

Declaração de um membro estático:

```java
	// ...
public static final double PI = 3.141592;		// valor é constante. uma vez atribuído, não será mudado.
public static void main(String[] args) {
    // ...
}
```

*Obs.: constantes têm, por padrão, os seus nomes maiúsculos. Exemplo: PI, NET_SALARY, etc.*

O código do problema de exemplo está abaixo.

**EsferaMainUm.java**

```java
package application;

import java.util.Scanner;
import java.util.Locale;

public class EsferaMainUm {
	
	public static final double PI = 3.14159;
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.print("Enter radius: ");
		double radius = sc.nextDouble();
		
		double c = circumference(radius);
		
		double v = volume(radius);
		
		System.out.printf("Circumference: %.2f%n", c);
		System.out.printf("Volume: %.2f%n", v);
		System.out.printf("PI value: %.2f%n", PI);
		
		sc.close();
	}
	
	
	// Criando as funcoes
	public static double circumference(double radius) {
		return 2 * PI * radius;
	}
	
	public static double volume(double radius) {
		return 4 * PI * Math.pow(radius, 3)/3;
	}
}
```

> Não podemos chamar um método não estático dentro de um método estático.

Por causa da colocação acima, os métodos `circumference(double radius)` e `volume(double radius)` devem ser estáticos, uma vez que estão sendo chamados **dentro** de um método **estático**, que é o método `main()`.

Readaptando o código exemplo acima criando uma classe utilitária chamada `Calculator`.

**EsferaMainUm.java**:

```java
package application;

import java.util.Scanner;
import java.util.Locale;
import util.Calculator;

public class EsferaMainUm {
	
	//public static final double PI = 3.14159;
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Calculator calc = new Calculator();
		
		System.out.print("Enter radius: ");
		double radius = sc.nextDouble();
		
		double c = calc.circumference(radius);
		
		double v = calc.volume(radius);
		
		System.out.printf("Circumference: %.2f%n", c);
		System.out.printf("Volume: %.2f%n", v);
		System.out.printf("PI value: %.2f%n", calc.PI);
		
		sc.close();
	}
	
	
	// Criando as funcoes
	//	public static double circumference(double radius) {
	//		return 2 * PI * radius;
	//	}
	//	
	//	public static double volume(double radius) {
	//		return 4 * PI * Math.pow(radius, 3)/3;
	//	}
}

```

**util.Calculator.java**

```java
package util;

public class Calculator {
	public final double PI = 3.14159;

	public double circumference(double radius) {
		return 2 * PI * radius;
	}
	
	public double volume(double radius) {
		return 4 * PI * Math.pow(radius, 3)/3;
	}
}

```

Como não havia método estático na classe **EsferaMainUm** foi necessário **instanciar** os objetos para que pudessem ser utilizados.

Porém, nesse caso em específico, os métodos `circumference()`, `volume()` e `PI` não dependem de objetos. Independentemente, o cálculo é da mesma forma. Assim, **tornemos eles estáticos**.

**Calculator.java**

```java
package util;

public class Calculator {
	public static final double PI = 3.14159;

	public static double circumference(double radius) {
		return 2 * PI * radius;
	}
	
	public static double volume(double radius) {
		return 4 * PI * Math.pow(radius, 3)/3;
	}
}
```

**EsferaMainUm.java**

```java
package application;

import java.util.Scanner;
import java.util.Locale;
import util.Calculator;

public class EsferaMainUm {
	
	//public static final double PI = 3.14159;
	
	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		//Calculator calc = new Calculator();
		
		System.out.print("Enter radius: ");
		double radius = sc.nextDouble();
		
		double c = Calculator.circumference(radius);
		
		double v = Calculator.volume(radius);
		
		System.out.printf("Circumference: %.2f%n", c);
		System.out.printf("Volume: %.2f%n", v);
		System.out.printf("PI value: %.2f%n", Calculator.PI);
		
		sc.close();
	}
	
	
	// Criando as funcoes
	//	public static double circumference(double radius) {
	//		return 2 * PI * radius;
	//	}
	//	
	//	public static double volume(double radius) {
	//		return 4 * PI * Math.pow(radius, 3)/3;
	//	}
}
```

### Encapsulamento

É um princípio que consiste em esconder detalhes de implementação de uma classe, expondo apenas operações seguras e que mantenham os objetos em um estado consistente. 

> **Regra de ouro:** o objeto deve sempre estar em um estado consistente e a própria classe deve garantir isso.

**Regra geral básica:** um objeto **não** deve expor nenhum atributo (modificador de acesso **private**). Os atributos devem ser acessados or meio de métodos `get` e `set`.

Por convenção, os métodos `get` e `set` são colocados **após** os construtores.

### Tipos referência vs. tipos valor

Classes são tipos referências. Variáveis cujo tipo são classes (`Produto`, por exemplo) não devem ser entendidas como caixas, mas como **ponteiros para caixas**.

`Stack`: onde ficam os endereços de memória e referências.

`Heap`: onde são alocados os objetos em **tempo de execução**.

Os tipos referência aceitam o valor `null`, que indica que a variável aponta para ninguém.

Tipos **primitivos** são tipos valor.

Quando instanciamos (i.e, usamos o operador `new`) qualquer tipo **estruturado**, são atribuídos valores padrão aos seus elementos. 

- Números: 0;
- boolean: false;
- char: caractere código 0;
- objeto: null.

| Classe                                                       | Tipo primitivo                                               |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| Vantagem: usufrui de todos os recursos OO                    | Vantagem: é mais simples e performático                      |
| Variáveis são **ponteiros**                                  | Variáveis são **caixas**                                     |
| Objetos precisam ser **instanciados** usando o `new` ou apontar para um obj. já existente | Não instancia. Uma vez declarados, estão prontos para uso    |
| Aceita valor `null`                                          | Não aceita valor `null`                                      |
| Y = X;<br />Objetos instanciados no **heap**                 | "Objetos" instanciados no **stack**                          |
| Objetos não utilizados são **desalocados** em um momento próximo pelo **garbage collector** | "Objetos" são desalocados imediamente quando seu escopo de execução é finalizado |

### Desalocação de memória

**Garbage collector:** processo que automatiza o **gerenciamento de memória** em tempo de execução. Ele monitora os objetos alocados dinamicamente pelo programa no heap, desalocando aqueles que não estão mais sendo utilizados.

Variáveis locais são desalocadas **imediatamente** assim que o seu escopo local sai de execução.

### Vetores

Vetor é um **arranjo unidimensional**.

**Arranjo** ou **Array** é uma estrutura de dados:

- **Homogênea:** os dados são do mesmo tipo;
- **Ordenada:** elementos são acessados por meio de **posições** ou **índices**;
- Alocado de uma vez só, em um bloco contíguo de memória

Vantagens do array: acesso **imediato** pela sua posição.

Desvantagens: tamanho fixo e dificuldade para se realizar **inserções** e **deleções**.

> **Problema exemplo 1.**
>
> Fazer um programa para ler um número inteiro N e a altura de N pessoas. Armazene as N alturas em um vetor. Em seguida, mostrar a altura média dessas pessoas.

> **Problema exemplo 2.**
>
> Criar um vetor de classes.
>
> **Cap10ex2**
>
> ```java
> package exercicios;
> 
> public class Cap10ex2 {
> 	
> 	private String name;
> 	private double price;
> 	
> 	public Cap10ex2(String name, double price) {
> 		this.name = name;
> 		this.price = price;
> 	}
> 	
> 	public void setName(String name) {
> 		this.name = name;
> 	}
> 	
> 	public String getName() {
> 		return this.name;
> 	}
> 	
> 	public void setPrice(double price) {
> 		this.price = price;
> 	}
> 	
> 	public double getPrice() {
> 		return this.price;
> 	}
> }
> ```
>
> **Cap10ex2Main**
>
> ```java
> package exercicios;
> 
> import java.util.Locale;
> import java.util.Scanner;
> 
> public class Cap10ex2Main {
> 	public static void main(String[] args) {
> 		
> 		Locale.setDefault(Locale.US);
> 		Scanner sc = new Scanner(System.in);
> 		
> 		int n = sc.nextInt();
> 		// Criando vetor de N produtos
> 		Cap10ex2[] vect = new Cap10ex2[n];
> 		
> 		for (int i=0; i<n; i++) {
> 			sc.nextInt();
> 			String name = sc.nextLine();
> 			double price = sc.nextDouble();
> 			vect[i] = new Cap10ex2(name, price);
> 		}
> 				
> 		double sum = 0.0;
> 		for (int i=0; i<vect.length; i++) {
> 			sum += vect[i].getPrice();
> 		}
> 		double avg = sum/n;
> 		System.out.printf("AVERAGE PRICE = %.2f", avg);
>         
>         
> 		sc.close();
> 	}
> }
> 
> ```

### Boxing, unboxing e wrapper classes

**Boxing:** processo de conversão de um objeto tipo **valor** para um objeto tipo **referência** compatível.

A classe `Object` é a classe mais **genérica** do Java, ela é mãe de todas as outras.

```java
int x = 20;		// caixa x = 20

Object obj = x;		// obj apontando com a seta para o Heap, encaixotou o objeto tipo valor
```

![](C:\Users\beatriz\Pictures\Screenpresso\2022-07-03_13h00_22.png)

**Unboxing:** processo de conversão de um objeto tipo **referência** para um objeto tipo **valor** compatível.

```java
int x = 20;

Object obj = x;

int y = (int) obj;		// pegou a caixa da Heap e desencaixou na variável y
```

![](C:\Users\beatriz\Pictures\Screenpresso\2022-07-03_13h20_24.png)

**Wrapper classes:** classes equivalentes aos tipos primitivos. Boxing e unboxing são naturais na linguagem. ***Uso comum**: campos de entidades em sistemas de informação, pois tipos referência (classes) aceitam valor null e usufruem dos recursos OO.*

![](C:\Users\beatriz\Pictures\Screenpresso\2022-07-03_13h39_28.png)

```java
int x = 20;

Integer obj = x;

int y = obj;
```

Perceba, no código acima, que não foi necessário fazer o ***casting***, que é o `int y = (int) obj`, pois estamos utilizando a **wrapper class equivalente ao int**.

### Laço "for each"

Sintaxe opcional e simplificada para percorrer coleções.

Sintaxe:

```java
for (Tipo apelido : coleção) {
    <comando 1>
    <comando 2>
}
```

O loop `for each` é como um `for` tradicional, porém existem diferenças relevantes entre eles. Enquanto o `for` tradicional permite que você acesse diretamente uma posição, bem como pule ou retroceda posições (no caso de só percorrer os índices pares, índices primos etc); o `for each`  não permite tal façanha. Isto é, através do `for each`  o vetor é percorrido índice a índice, do início ao fim. Ao utilizar o `for each` através da coleção, **não** temos um vetor, o que temos é o próprio elemento, o próprio valor em si.

```java
package application;

public class ForEach {
	public static void main(String[] args) {
		
		// Instancia de um vetor de 3 posições já com os seus respectivos valores
		String[] vect = new String[] {"Maria", "Bob", "Alex"};
		
//		for (int i=0; i<vect.length; i++) {
//			System.out.println(vect[i]);
//		}
		
		for (String obj : vect) {
            // dará erro de compilação
//			System.out.println(obj[0]);		
            System.out.println(obj);		// implementação correta
		}
	}
}
```

A leitura é:

> Para cada objeto 'obj' contido em vect, faça.

### Listas

**Lista** é uma **estrutura de dados**, onde ela é:

- **homogênea**: dados do mesmo tipo;
- **ordenada**: elementos acessados por meio de posições;
- inicia **vazia**, onde os elementos são alocados sob demanda;
- cada elemento ocupa um **nó** (ou **nodo**) da lista; e
- **não** aceita **tipos primitivos**, deve ser utilizado os **wrappers** (Integer, Character, Double ao invés de int, char e double).

Cada nodo conhece o seu valor e aponta para o próximo nodo. Em uma lista encadeada simples, um nodo aponta para o próximo, que aponta para o próximo após dele e assim vai. Não tem "apontamento de volta" e nem apontamento "regressivo".

**Wrapper** de lista é: **List**, que **não** é uma classe, mas sim uma **interface**. Isto quer dizer que o tipo `List` define apenas a **especificação** das operações. A instância de interfaces não são diretas, é necessário criar uma classe que **implementa** a interface e, logo após, instancia a classe criada. 

Duas classes que **implementam** a interface `List`:

- `ArrayList`;
- `LinkedList`; etc.

**Vantagens:** tamanho **variável**, facilidade para se realizar **inserções** e **deleções**.

**Desvantagens:** acesso **sequencial** aos elementos.

No exemplo da aula 99, vamos criar uma List de números inteiros.

### Matrizes

Matrizes são **arranjos bidimensionais**. Trata-se, portanto, de uma estrutura de dados. As suas características são:

- **Homogênea:** dados do mesmo tipo;
- **Ordenada:** elementos acessados por meio de posições;
- Alocada de uma vez só, em um bloco contíguo de memória.

**Vantagens:** acesso **imediato** aos elementos pela sua **posição**.

**Desvantagens:** tamanho **fixo**, **dificuldade** para se realizar **inserções** e **deleções**.

### Date em Java

Representa um **instante** com precisão de milisegundos.

**Documentação completa da classe:** https://docs.oracle.com/javase/10/docs/api/java/util/Date.html

Um objeto `Date` armazena internamente o **número de milissegundos desde a meia noite do dia 1 de janeiro de 1970 GMT (UTC)**.

A classe `SimpleDateFormat` (https://docs.oracle.com/javase/10/docs/api/java/text/SimpleDateFormat.html) define formatos para **conversão entre `Date`** e `String`. Exemplo:

> dd/MM/yyyy -> 07/07/1998
>
> dd/MM/yyyy HH:mm:ss-> 07/07/1998 09:30:05

**Padrão ISO 8601:** padrão muito utilizado para se armazenar datas na forma de texto. Formato:

>yyyy-MM-ddTHH:mm:ssZ
>
>Ano - mês - diaThora:minuto:segundoZ
>
>"2018-06-25T15:42:07Z"
>
>O Z indica que é o padrão UTC.

É uma **boa prática** armazenar datas no formato **UTC** e quando exibir na tela, aí sim converter para o horário local. :)

O Java 8+ tem uma **nova versão** para **trabalhar com datas**. Um dos recursos é a **classe `Instant`**, que possui uma função chamada `parse("")`. Se colocar uma String no formato ISO 8601, automaticamente ele pode ser convertido para o tipo `Date` do Java. Exemplo:

> `Date y3 = Date.from(Instant.parse("2018-06-25-T15:42:07Z"));`

### Nivelamento sobre Git e Github

**Git:** sistema de **versionamento**, onde você controla as modificações de um projeto por meio de versões chamadas **commits**.

#### Repositório remoto e local

**Repositório:** projeto que é controlado pelo GIT, por exemplo as versões e o histórico de evolução são controlados pelo Git.

**Repositório remoto:** onde guarda a cópia "oficial" do projeto, em um servidor.

Cada pessoa que trabalha no projeto, pode fazer uma cópia do repositório em seu computador, criando, portanto, um **repositório local.**

#### Configurando o usuário no git

```shell
git config --global user.name "Beatriz Gomes"

git config --global user.email "biangomes0707@gmail.com"
```

Para conferir as configurações:

```shell
git config --list
```

#### Configurar chave SSH para o Github

**SSH:** protocolo para comunicação de dados com segurança. Basicamente, é o cadastro prévio de computadores que podem acessar o github em seu nome. Comando para ser rodado no git bash.

```shell
# Algoritmo Ed25519
ssh-keygen -t ed25519 -C "biangomes0707@gmail.com"

# Caso não seja compatível com o algoritmo acima, rodar o comando abaixo
ssh-keygen -t rsa -b 4096 -C "biangomes0707@gmail.com"
```

Inicializamos um projeto **git** digitando no git bash da pasta em questão `git init`.

Quando fazemos `git add .` salvamos a versão em uma pasta temporária chamada **stage**.

- [ ] concluir o topico de nivelamento de git e github

### Enumerações

É um tipo especial que serve para **especificar de forma literal** um conjunto de **constantes** relacionadas. A palavra-chave, em Java, é: `enum`.

**Exemplo:**

![image-20220718120429903](C:\Users\beatriz\Pictures\Screenpresso\2022-07-18_12h04_28.png)

Dado o fluxograma acima, como a representaríamos em Java?

```java
package entities.enum;

public enum OrderStatus {
    PENDING_PAYMENT,
    PROCESSING,
    SHIPPED,
    DELIVERED;
}
```

O que tem dentro da enum `OrderStatus` são os **estados possíveis** de um pedido.

Vamos aplicar ao dia a dia.

```java
public enum SituacaoDeProtocolo {
    SALVAR,
    PROTOCOLOAR_DEPOIS,
    PROTOCOLAR_E_ENVIAR;
}
```

```java
public class Protocolar() {
    private Integer nuProtocolo;
    private String usuarioCadastrante;
    private Date dtProtocolo;
    private SituacaoDeProtocolo statusProtocolo;
    
    public Protocolar() {
    }
    
    public Protocolar(int nuProtocolo, String usuarioCadastrante, Date dtProtocolo, SituacaoDeProtocolo statusProtocolo) {
        this.nuProtocolo = nuProtocolo;
        this.usuarioCadastrante = usuarioCadastrante;
        this.dtProtocolo = dtProtocolo;
        this.statusProtocolo = statusProtocolo;
    }
}
```

**Conversão de String para enum**

```java
OrderStatus os1 = OrderStatus.DELIVERED;

OrderStatus os2 = OrderStatus.valueOf("DELIVERED");
```

A notação **UML** correspondente ao `enum` é:

![](C:\Users\beatriz\Pictures\Screenpresso\2022-07-18_12h40_50.png)

### Vamos falar um pouco sobre design

**Categoria de classes**

Podemos pensar nas classes como **categorias**. Por questões de design como **organização**, **flexibilidade**, **reuso**, **delegação**, etc., há várias **categorias de classes**. São algumas delas:

- **Views**: telas do sistema;
- **Controllers**: o "meio de campo" entre a **tela** e o **sistema**;
- **Entities**: entidades de negócios como Produtos, Pedido, Cliente, etc.;
- **Services**: interface, por exemplo;
- **Repositories**: objetos responsáveis por acessar os dados em um banco de dados (por exemplo).

### Composição

É um tipo de **associação** que permite que um objeto contenha outro. Relação "tem-um" ou "tem-vários".

**Vantagens:** 

- Organização: divisão de responsabilidades;
- Coesão;
- Flexibilidade;
- Reuso.

**Exercício de composição de objetos**

![](C:\Users\beatriz\Pictures\Screenpresso\2022-07-18_13h07_43.png)

**Representação de um relacionamento 1:1 (Worker e Department):**

*classe `Worker`*

```java
public class Worker() {
    ...
    private Department department;
    ...
}
```

**Representação de um relacionamento 1:N (Worker e HourContract):**

*classe `Worker`*

```java
private List<HourContract> contracts;
```

Como objetos `List` precisam ser **inicializados**, deve ser feito da seguinte forma:

*classe `Worker`*

```
private List<HourContract> contracts = new ArrayList<>();
```

![image-20220723095230580](C:\Users\beatriz\AppData\Roaming\Typora\typora-user-images\image-20220723095230580.png)



No dia a dia no desenvolvimento, quando precisamos trabalhar com coleções de objetos, não se declara vetores, arrays. É utilizado **`Collections`**.

A criação de métodos estáticos evita que para cada novo objeto seja criado uma nova instância do método estático. Assim que se declara:

> `private static Classe c = new Classe();`

### Herança

Herança permite o reuso de atributos e métodos (dados e comportamento).

Vamos fazer a abstração de uma conta.

Criamos uma classe chamada `Account` com os seguintes atributos: `name`, `holder` e `balance`. Em código, ela está da seguinte forma:

```java
package secao14.heranca.entities;

public class Account {

    private Integer number;
    private String holder;
    /*
    private Double balance;

    *   Se o atributo balance estiver protected, ele nao será acessado pela subclasse BusinessAccount.
    *   Desta forma, o modificador de acesso dele será protected, pois ele precisa estar seguro também.

     */
    protected Double balance;

    public Account() {}

    public Account(Integer number, String holder, Double balance) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Double getBalance() {
        return balance;
    }


    public void withdraw(double amount) {
        balance = balance - amount - 5.0;
    }

    public void deposit(double amount) {
        balance = balance + amount;
    }
}

```

Agora, criemos uma classe **filha** chamada `BusinessAccount`, como se fosse uma conta corporativa.

```java
package secao14.heranca.entities;

public class BusinessAccount extends Account {

    private Double loanLimit;

    public BusinessAccount() {
        super();
    }

    public BusinessAccount(Integer number, String holder, Double balance, Double loanLimit) {
        super(number, holder, balance);
        this.loanLimit = loanLimit;
    }

    public Double getLoanLimit() {
        return loanLimit;
    }

    public void setLoanLimit(Double loanLimit) {
        this.loanLimit = loanLimit;
    }

    public void loan(double amount) {
        if (loanLimit >= amount) {
            balance = balance + amount - 10.0;
        }
    }
}
```

**Características:**
- Relação "é-um";
- Generalização (`Account`) / Especialização (`BusinessAccount`);
- Superclasse (classe base; `Account`) / subclasse (classe derivada; `BusinessAccount`);
- Herança / extensão (`extends` da declaração da subclasse);
- Herança é uma **associação entre classes** e não entre objetos. Esta característica é o que diferencia herança de composição.


### Upcasting e downcasting

**Upcasting:** casting da sublcasse para superclasse. **_Exemplo_:** eu tenho um objeto do tipo `BusinessAccount` e quisesse atribuir a um objeto do tipo `Account`.
Uso comum: polimorfismo.

**Downcasting:** casting da superclasse para a subclasse. **_Exemplo_:** eu tenho um objeto do tipo `Account` e quero atribuir a um objeto do tipo `BusinessAccount`. Para fazer downcasting é necessário utilizar o operador `instanceOf()`.
Uso comum: métodos que recebem parâmetros genéricos como o `Equals`.

Para testar o upcasting e downcasting com as classes criadas acima, o nosso programa ficou assim.

Vamos criar a classe `SavingsAccount`:
```java
package secao14.heranca.entities;

public class SavingsAccount extends Account {
    private Double interestRate;

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(Integer number, String holder, Double balance, Double interestRate) {
        super(number, holder, balance);
        this.interestRate = interestRate;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void updateBalance() {
        balance += balance * interestRate;
    }
}
```

E, para testar os conceitos aprendidos acima, vamos chamar o `Program`.

```java
package secao14.heranca.application;

import secao14.heranca.entities.Account;
import secao14.heranca.entities.BusinessAccount;
import secao14.heranca.entities.SavingsAccount;

public class Program {
    public static void main(String[] args) {

        Account acc = new Account(1001, "Alex", 0.0);
        BusinessAccount bacc = new BusinessAccount(1002, "Maria", 0.0, 500.00);

        // Upcasting
        Account acc1 = bacc;
        Account acc2 = new BusinessAccount(1003, "Bob", 0.0, 200.00);
        Account acc3 = new SavingsAccount(1004, "Taylor", 10000.00, 0.01);

        // Downcasting
        BusinessAccount acc4 = (BusinessAccount)acc2;
        acc4.loan(200.00);

        //BusinessAccount acc5 = (BusinessAccount)acc3;       erro em tempo de execução
        if (acc3 instanceof BusinessAccount) {
            BusinessAccount acc5 = (BusinessAccount) acc3;
            acc5.loan(2000.00);
            System.out.println("Loan!");
        }

        if (acc3 instanceof SavingsAccount) {
            SavingsAccount acc5 = (SavingsAccount) acc3;
            acc5.updateBalance();
            System.out.println("Update!");
        }
    }
}
```

### Sobreposição, palavra super, anotação @Override

A **sobreposição** ou **sobrescrita** é a implementação de um método de uma **superclasse** na **subclasse**.

É fortemente recomendável usar a anotação `@Override` em um método sobrescrito, pois facilita a leitura e compreensão do código, além de avisar ao compilador, tornando-se, assim, uma **boa prática**.

No exemplo anterior seria a implementação do método `void withdraw()` na subclasse `BusinessAccount`, por exemplo.

Suponhamos que a operação `withdraw()` possui uma taxa no valor de R$500.00. No entanto, se a conta for do tipo **poupança** (`SavingsAccount`) esta taxa não deve ser cobrada.

A notação do método `withdraw` sobrescrito é (_obs.: na subclasse `SavingsAccount`_):

```java
@Override
    public void withdraw(double amount) {
        balance -= amount;
    }
```

É possível chamar a implementação da superclasse usando a palavra **`super`**.

Vamos fazer na subclasse `BusinessAccount` a operação `withdraw()` normal com um desconto de mais 2 reais.

```java
    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);     // realiza a operaçao withdraw da classe mae normal
        balance -= 2.0;             // e retira mais R$2,00
    }

```

### Classes e métodos `final`

**Palavra-chave:** `final`.

**Classe:** aplicada na classe, evita que ela seja herdada.

`public final class SavingsAccount() {}`

**Método:** aplicada no método, evita que sele seja sobreposto.

***Para que podemos querer usar `final`***?

> **Segurança:** dependendo da regra de negócio, pode ser que seja desejável que uma classe ou método não seja herdado(a);
>
> É **conveniente** acrescentar `final` em **métodos sobrepostos**, pois sobreposições múltiplas podem ser uma **porta de entrada** para **inconsistências**.
>
> **Performance:** atributos de tipo de uma classe `final` são analisados mais **rapidamente** em **tempo de execução**. Exemplo: `String`.

### Introdução ao Polimorfismo

Em POO, polimorfismo é recurso que permite que variáveis de um mesmo tipo mais genérico apontem para objetos de tipos específicos diferentes, tendo, assim, comportamentos diferentes conforme cada tipo específico.

```java
Account x = new Account(1020, "Alex");
Account y = new SavingsAccount(1023, "Maria", 0.01);


x.withdraw(200);
y.withdraw(200);
```

# Classes abstratas

Classes que **não** podem ser **instanciadas**. É uma forma de garantir herança total: somente **subclasses não abstratas** podem ser instanciadas, mas nunca a superclasse abstrata.

*Obs.: é **instanciada** e não herdada.*

Por que criamos uma classe que não pode ser instanciada?

R.: **reuso** e **polimorfismo**. Este entra pq a superclasse genérica nos permite tratar de forma fácil e uniforme todos os tipos de conta.

> Demo: totalizar o saldo de todas as contas e depositar $ 10.00 em todas as contas.

# Métodos abstratos

Métodos que não possuem implementação. Estes precisam ser abstratos quando a classe é genérica demais para conter a sua implementação.

Se uma classe possuir **pelo menos um método abstrato**, então esta classe **também é abstrata**.

**Código da aula 165**

`Shape.java`

```java
package secao14.exercicios.entities;

import secao14.exercicios.entities.enums.Color;

public abstract class Shape {
    private Color color;

    public Shape() {
    }

    public Shape(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract Double area();
}

```

`Circle.java`

```java
package secao14.exercicios.entities;

import secao14.exercicios.entities.enums.Color;

public class Circle extends Shape {

    private Double radius;

    public Circle() {
        super();
    }

    public Circle(Color color, Double radius) {
        super(color);
        this.radius = radius;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public Double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Radius: " + String.format("%.2f", radius)
                + " Area = " + radius + " x " + String.format("%.2f", Math.PI)
                + " = " + String.format("%.2f", area());
    }
}

```

`Rectangule.java`

```java
package secao14.exercicios.entities;

import secao14.exercicios.entities.enums.Color;

public class Rectangule extends Shape {

    private Double width;
    private Double height;

    public Rectangule() {
        super();
    }

    public Rectangule(Color color, Double width, Double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public Double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Width: " + String.format("%.2f", width)
                + " Height: " + String.format("%.2f", height)
                + " Area = " + width + " x " + height
                + " = " + String.format("%.2f", area());
    }
}

```

`Color.java`

```java
package secao14.exercicios.entities.enums;

public enum Color {
    BLACK,
    BLUE,
    RED;
}

```

`MainShape.java`

```java
package secao14.exercicios.application;

import secao14.exercicios.entities.Circle;
import secao14.exercicios.entities.Rectangule;
import secao14.exercicios.entities.Shape;
import secao14.exercicios.entities.enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainShape {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Shape> list = new ArrayList<Shape>();

        System.out.println("Enter the number of shapes: ");
        int n = sc.nextInt();

        for (int i=0; i<n; i++) {
            System.out.println("Shape of #" + i + " data: ");
            System.out.print("Rectangule or Circle (r/c)? ");
            char ch = sc.next().charAt(0);
            System.out.print("Color (BLACK/BLUE/RED): ");
            Color color = Color.valueOf(sc.next());

            if (ch == 'r') {
                System.out.println("Enter the width: ");
                double width = sc.nextDouble();
                System.out.println("Enter the height: ");
                double height = sc.nextDouble();

                list.add(new Rectangule(color, width, height));
            } else {
                System.out.print("Enter the radius: ");
                list.add(new Circle(color, sc.nextDouble()));
            }
        }

        for (Shape shape : list) {
            System.out.println(shape);
        }

        sc.close();
    }
}

```

# Seção 15: Tratamento de Exceções

### Exceções

Uma exceção é qualquer **condição de erro** ou **comportamento inesperado** encontrado por um programa **em execução**.

Em Java, uma exceção é um **objeto herdado da classe**:

- `java.lang.Exception`: o compilador obriga a **tratar** ou **propagar**;
- `java.lang.RuntimeException`: o compilador **não** obriga a tratar ou propagar.

Quando lançada, uma exceção é propagada na **pilha de chamadas de métodos em execução**, até que ela seja capturada/tratada ou o programa seja encerrado.

A exception `IndexOutOfBoundsException` é quando se tenta acessar uma posição que **não existe**, ou seja, ultrapassa o `length`. Já a exception `NullPointerException` é quando se é tentado acessar uma variável que recebe `Null`.

#### Por que exceções?

Trata-se de uma boa prática, pois permite que os erros sejam tratados de forma consistente e flexível.

- **Vantagens:**
  - delega a lógica do erro para a classe responsável por conhecer as regras;
  - Trata de forma organizada exceções de tipos diferentes;
  - A exceção pode carregar dados quaisquer (???)

### Estrutura try-catch

**Bloco `try`**:  contém o código que representa a **execução normal do trecho** que **pode** acarretar em uma exceção.

**Bloco `catch`**: possui o código a ser executado caso a **exceção ocorra**.

**Sintaxe**

```java
try {
    ...
}
catch (Exception e) {
    ...
}
catch (Exception e) {
    ...
}
```

**Código da aula:**

```java
package secao15.application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            String[] vect = sc.nextLine().split(" ");
            int posicao = sc.nextInt();
            System.out.println(vect[posicao]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida!");
        }
        catch (InputMismatchException e) {
            System.out.println("Input error!");
        }
        sc.close();
    }
}
```

