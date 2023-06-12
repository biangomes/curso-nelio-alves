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

### Pilha de chamadas de métodos - stack trace

Para imprimir o stack trace basta seguir o código a seguir:

```java
public static void method2() {
        System.out.println("*** METHOD2 STARTS *** ");
        Scanner sc = new Scanner(System.in);

        try {
            String[] vect = sc.nextLine().split(" ");
            int posicao = sc.nextInt();
            System.out.println(vect[posicao]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida!");
            e.printStackTrace();
            sc.next();
        }
```

O `StackTrace` mostra uma estrutura de onde o erro foi disparado e todas as vezes que ele foi "invocado" por assim dizer. No IntelliJ é possível a sua estrutura mais precisamente. Considere todo o código a seguir:

**StackTrace.java**

```java
package secao15.application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StackTrace {
    public static void main(String[] args) {
        //System.out.println("*** METHOD STARTS *** ");

        method1();
        System.out.println("End of program");
    }

    public static void method1() {
        System.out.println("*** METHOD1 STARTS *** ");
        method2();
        System.out.println("*** METHOD1 ENDS ***");
    }

    public static void method2() {
        System.out.println("*** METHOD2 STARTS *** ");
        Scanner sc = new Scanner(System.in);

        try {
            String[] vect = sc.nextLine().split(" ");
            int posicao = sc.nextInt();
            System.out.println(vect[posicao]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Posição inválida!");
            e.printStackTrace();
            sc.next();
        }
        catch (InputMismatchException e) {
            System.out.println("Input error!");
        }

        sc.close();

        System.out.println("*** METHOD2 ENDS ***");
    }
}

```

![](C:\Users\beatr\Pictures\stacktrace.PNG)

### Bloco `finally`

É um bloco que contém código a ser executado independentemente de ter corrido ou não uma exceção. Exemplo clássico: fechar um arquivo, conexão de banco de dados ou outro recurso específico **ao final do processamento**.

Sintaxe genérica: 

```java
try {
    // ...
}
catch (ExceptionType e) {
    // ...
}
finally {
    // ...
}
```

> **Extra:** Código base para percorrer as linhas entre um arquivo.
>
> ```java
> File file = new File("C:\\temp\\arq_exemplo.txt");
> sc = new Scanner(file);
> 
> while (sc.hasNextLine()) {
>     System.out.println(sc.nextLine());
> }
> ```



### Criando exceções personalizadas

**Sugestão de pacotes "model"**:

- model
  - entities
  - enums
  - exception
  - exception

**Questão problema**:

> Fazer um programa para ler os dados de uma reserva de hotel (número do quarto, data
> de entrada e data de saída) e mostrar os dados da reserva, inclusive sua duração em
> dias. Em seguida, ler novas datas de entrada e saída, atualizar a reserva, e mostrar
> novamente a reserva com os dados atualizados. O programa não deve aceitar dados
> inválidos para a reserva, conforme as seguintes regras:
> \- Alterações de reserva só podem ocorrer para datas futuras
> \- A data de saída deve ser maior que a data de entrada

![](C:\Users\beatr\Pictures\reservation-UML.png)

---

### Aula 44. Estruturas repetitivas "Enquanto" (`while`)

Estrutura de controle que **repete** um bloco de comandos **enquanto** a **condição** é **verdadeira**. **Dica de uso:** quando **não** se sabe previamente a quantidade de repetições que serão realizadas.

**Sintaxe**

```java
while (condicao) {
    comando 1;
    comando 2;
}
```



**Problema exemplo:**

> Fazer um programa que leia números inteiros até que um **zero** seja lido. Ao final mostra a soma dos números lidos.

**Minha solução:**

```java
package secao6;

import java.util.Scanner;

public class aula44 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        
        System.out.println("Digite o número com que deseja iniciar: ");
        int num_escolhido = sc.nextInt();
        int soma=0;

        while (num_escolhido != 0) {
            soma += num_escolhido;
            System.out.println("Digite um número: ");
            num_escolhido = sc.nextInt();
        }

        System.out.println("Soma dos números anteriores ao zero: " + soma);

    }
}
```

### Aula 60. Funções (sintaxe)

Principais vantagens: modularização, delegação e reaproveitamento.

Em orientação a objetos, as funções em classes recebem o nome de **métodos**.

Quando estamos na função **Main** e queremos criar uma função, deixamos abaixo da estrutura do `public static void main(...)` e acima do `}` do `public class`. Vejamos o exemplo abaixo:

```java
package secao7;

import java.util.Scanner;

public class funcoes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("entre com tres numeros: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int higher = max(a, b, c);

//        if (a > b && a > c) {
//            System.out.println("maior número = " + a);
//        } else if (b > c) {
//            System.out.println("maior número = " + b);
//        } else {
//            System.out.println("maior número = " + c);
//        }
        sc.close();
    }

    // Função que retorna o maior de três números
    public static int max(int a, int b, int c) {
        int maior=0;

        if (a > b && a > c) {
            maior=a;
        } else if (b > c) {
            maior = b;
        } else {
            maior = c;
        }
        
        return maior;
    }
}
```

Deixamos `public static int max(...)`, pois é uma função que existirá independente da criação de um objeto.

### Aula 214. Lendo arquivo texto com classe `File` e `Scanner`

**File** - representação abstrata de um arquivo e o seu caminho;

**Scanner** - leitor de texto;

**IOException (Exception)** - exceção padrão, entrada e saída, quando se trabalha com **arquivos**. *Obs.: herda de `Exception`, isto quer dizer que precisa ser tratada*.

### Aula 215. `FileReader` e `BufferedReader`

**FileReader** - stream de leitura de caracteres a partir de arquivos

**BufferedReader (mais rápido)** - é instanciado a partir do **FileReader** e implementa algumas otimizações utilizando buffered memória, tornando-se mais rápido que o FileReader.

### Aula 216. Bloco `try-with-resources`

É um bloco `try` que declara um ou mais recursos, além de **garantir** que esses recursos serão **fechados** ao final do bloco. Disponível a partir do Java 7.

Nessa aula foi usado como base o mesmo código da aula 215.

**Aula215.java**

```java
package secao17.application;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Aula215 {
    public static void main(String[] args) throws IOException {

        String path = "files\\in.txt";
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);        // instanciado a partir do FileReader, uma camada de abstracao acima

            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```



**Aula216.java**

```java
package secao17.application;

import java.io.*;

public class Aula216 {
    public static void main(String[] args) throws IOException {

        String path = "files\\in.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

No escopo do `try` já é instanciado as `streams` de `BufferedReader` e `FileReader`.

### Aula 217. `FileWriter` e `BufferedWriter`

**FileWriter** é uma **stream** de escrita de caracteres de arquivos.

- **Cria/recria** o arquivo: `new FileWriter(path);`
- **Acrescenta ao arquivo existente:** `new FileWriter(path, true);`

**BufferedWriter** é o mesmo conceito, porém **mais rápido**.

**Aula217.java**

```java
package secao17.application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class Aula217 {
    public static void main(String[] args) {

        String[] lines = new String[] {
                "Good morning",
                "Good afternoon",
                "Good night"};

        String path = "files\\out.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

Se adicionarmos o argumento `true` no bloco `try`, ele não irá recriar o arquivo, apenas adicionar na última linha.

### Aula 218. Manipulando pastas com `File`

**secao17.application.Aula218.java**

```java
import java.io.File;
import java.util.Scanner;

public class secao17.application.Aula218 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com um caminho de diretório: ");
        String strPath = sc.nextLine();

        File path = new File(strPath);

        // Coletando todas as pastas contidas no caminho informado pelo usuario
        File[] folders = path.listFiles(File::isDirectory);
        System.out.println("FOLDERS:");
        for (File folder : folders) {
            System.out.println(folder);
        }

        // Listagem de ARQUIVOS
        File[] files = path.listFiles(File::isFile);
        System.out.println("FILES:");
        for (File file : files) {
            System.out.println(file);
        }

        // Criando SUBPASTAS
        boolean success = new File(strPath + "\\subdir").mkdir();
        System.out.println("Diretório criado com sucesso? " + success);

        sc.close();
    }
}
```

# Seção 18: Interfaces

### Aula 224. Interfaces

A partir do Java 8, interfaces podem ter "default methods".

**Definição clássica de interfaces**

Interface é um **tipo** que **define** um conjunto de operações que uma classe deve implementar. Ela estabelece um **contrato** que as classes que implementarão a interface devem cumprir. O objetivo de utilizar interfaces é **criar sistemas com _baixo acoplamento_ e _flexíveis_**.

**Exemplo:**

```java
public interface Shape {
    double area();
    double perimeter();
}
```

**Problema exemplo**

> Uma locadora brasileira de carros cobra um valor por hora para locações de até 12 horas. Porém, se a duração da locação ultrapassar 12 horas, a locação será cobrada com base em um valor diário. Além do valor da locação, é acrescido no preço o valor do imposto conforme regras do país que, no caso do Brasil, é 20% para valores até 100.00, ou 15% para valores acima de 100.00. Fazer um programa que lê os dados da locação (modelo do carro, instante inicial e final da locação), bem como o valor por hora e o valor diário de locação. O programa deve então gerar a nota de pagamento (contendo valor da locação, valor do imposto e valor total do pagamento) e informar os dados na tela. Veja os exemplos.

![](C:\Users\beatr\Pictures\Screenpresso\exemplo1-interfaces.png)

**Projeto:** https://github.com/biangomes/rent-a-car

### Aula 228. Inversão de controle e injeção de dependência

Quando associamos uma classe diretamente a outra criamos um **acoplamento forte**. Se a classe concreta mudar, é preciso mudar a classe que se associou a primeira. Seriam **dois pontos de alteração.**

Quando criamos uma interface, a classe que se associou a primeira passa a **implementar** a interface. E a associação, no entanto, se torna entre a interface e a classe primeira criando um **acoplamento fraco**.

No projeto anterior, tínhamos, dentre outros, dois services: `RentalService` e `BrazilTaxService`. Este era o escopo das respectivas entidades:

```java
package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;
    private BrazilTaxService brazilTaxService;

    public RentalService(double pricePerHour, double pricePerDay, BrazilTaxService brazilTaxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.brazilTaxService = brazilTaxService;
    }

    public void processInvoice(CarRental carRental) {

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;

        if (hours <= 12.0) {
            basicPayment = pricePerHour * Math.ceil(hours);     // Math.ceil => round number up
        } else {
            basicPayment = pricePerDay * Math.ceil(hours/24);
        }

        double tax = brazilTaxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
```

```java
package model.services;

public class BrazilTaxService {

    public double tax(double amount) {
        if (amount <= 100.0) {
            return amount * 0.2;
        } else {
            return amount * 0.15;
        }
    }
}
```

Por fim, o programa principal, `Program`:

```java
package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Set a datetime pattern
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Enter with rent data");
        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.print("Withdraw (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Devolution (dd/MM/yyyy hh:mm): ");
        LocalDateTime end = LocalDateTime.parse(sc.nextLine(), dtf);


        CarRental carRental1 = new CarRental(start, end, new Vehicle(carModel));

        System.out.print("Price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        rentalService.processInvoice(carRental1);
        System.out.println("DEBT");
        System.out.println("Basic payment: " + String.format("%.2f", carRental1.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", carRental1.getInvoice().getTax()));
        System.out.println("Total payment: " + String.format("%.2f", carRental1.getInvoice().getTotalPayment()));

        sc.close();
    }
}
```



No escopo de negócio, quando calculamos o gasto total de se alugar um carro devemos considerar as taxas de juros brasileiras. O problema é que se trocarmos essa taxa de juros, por exemplo taxa de juros dos EUA, teríamos que modificar tanto no programa principal, quanto no `RentalService` e criar um novo `Service` correspondente a nova taxa de juros.

Refatorando a aplicação, foi criada uma `interface` no mesmo domínio dos `services` acima chamada `TaxService`, em que vai generalizar o cálculo de uma taxa de juros. O `BrazilTaxService` permanece, porém ele implementará os métodos da interface. Veja como ficou a seguir: `RentalService`, `TaxService`, `BrazilTaxService` e o `Program`, respectivamente.

```java
package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;
    private TaxService taxService;

    
    // o terceiro argumento pode receber BrazilTaxService, EuaTaxService, 	MexicoTaxService, etc...
    public RentalService(double pricePerHour, double pricePerDay, TaxService taxService) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxService = taxService;
    }

    public void processInvoice(CarRental carRental) {

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;

        if (hours <= 12.0) {
            basicPayment = pricePerHour * Math.ceil(hours);     // Math.ceil => round number up
        } else {
            basicPayment = pricePerDay * Math.ceil(hours/24);
        }

        double tax = taxService.tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
```

```java
package model.services;

public interface TaxService {
    double tax(double amount);
}
```

```java
package model.services;

public class BrazilTaxService implements TaxService {

    public double tax(double amount) {
        if (amount <= 100.0) {
            return amount * 0.2;
        } else {
            return amount * 0.15;
        }
    }
}
```

```java
package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Set a datetime pattern
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Enter with rent data");
        System.out.print("Car model: ");
        String carModel = sc.nextLine();
        System.out.print("Withdraw (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Devolution (dd/MM/yyyy hh:mm): ");
        LocalDateTime end = LocalDateTime.parse(sc.nextLine(), dtf);


        CarRental carRental1 = new CarRental(start, end, new Vehicle(carModel));

        System.out.print("Price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        rentalService.processInvoice(carRental1);
        System.out.println("DEBT");
        System.out.println("Basic payment: " + String.format("%.2f", carRental1.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", carRental1.getInvoice().getTax()));
        System.out.println("Total payment: " + String.format("%.2f", carRental1.getInvoice().getTotalPayment()));

        sc.close();
    }
}
```

A interface ficou simples, pois a única responsabilidade dela é **abstrair** o método de cálculo de taxa, haja visto que se precisassemos cuidar de `EuaTaxaService`, por exemplo, ele teria o próprio método de taxa, isto é, com os seus próprios valores e "condicionais".

A metodologia acima é **inversão de controle por meio de construtor**. 

A **inversão de controle** é um padrão de desenvolvimento que consiste em retirar da classe a responsabilidade de instanciar as suas dependências.

A **injeção de dependência**, por sua vez, é uma forma de realizar a inversão de controle: componente **externo** (`TaxService`) instancia a dependência, que é então injetada no objeto "pai". Pode ser implementada de várias formas:

- construtor;
- classe de instanciação (builder/factory)/
- container/framework

### Aula 223. Herdar vs Cumprir contrato

**Aspectos em comum entre herança e interfaces:**

- Relação é-um;
- Generalização/especialização
- Polimorfismo

A semelhança no polimorfismo entre as duas se dá porque, **em tempo de execução**, ambas podem se associar com um objeto **concreto**. 

**Diferença fundamental:**

- Herança: reuso;
- Interface: contrato a ser cumprido.

Quando criamos uma classe que implementa uma interface, os métodos definidos na interface **não são reutilizados** e sim **implementados**.

O conceito clássico de uma interface é a definição de um contrato.

**Questionamento:**

> E se eu precisar implementar `Shape` como uma interfae, porém também quiser definir uma estrutura comum reutilizável para todas as figuras?

Quando criamos a interface já presumimos que o método é **público** e **abstrato**.


Supondo que tenhamos uma interface chamada `Shape`:

```java
package secao18.entities;

public interface Shape {

    // todo objeto que implementar Shape, deve implementar o metodo abaixo
    double area();
}
```

e agora queremos criar uma implementação de `Shape`, mas que tenha estruturas reutilizáveis (conforme questionado).

Para isso criaremos uma classe chamada `AbstractShape`:

```java
package secao18.entities;

public class AbstractShape implements Shape {
    
    private Color color;

    public AbstractShape(Color color) {
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    
}
```

O compilador irá reclamar, pois, como dito anteriormente, uma interface estabelece **contratos** que devem ser cumpridos por **todas** as classes que a **implementarem** e, nesse caso, a classe `AbstractShape` não implementa o método `area()`. Quem irá implementar são as **classes filhas** AbstractShape. Para resolver o problema apontado pelo compilador, diremos que se trata de uma classe **abstrata**.

```java
public abstract class AbstractShape implements Shape {
    // ...
}
```

### Aula 233. Herança múltipla e o problema do diamante

Quando no diagrama o nome do método está em itálico, quer dizer que é um método **abstrato**. 

**Problema do diamante:** existência do mesmo método em mais de uma superclasse (gera uma ambiguidade). É gerado por **herança múltipla**, que no Java não é permitido.

Criamos uma classe chamada `Device`, `Printer`, `Scanner` e `ComboDevice`, em que a modelagem é `Device` como uma super classe, `Printer` e `Scanner` extendem-na e `ComboDevice` extende de `Printer` e `Scanner`. 

É apresentada a mensagem no IntelliJ após fazer o último passo:
> Class cannot extend multiple classes

Apesar de uma classe não poder estender mais de uma classe, ela pode **implementar** mais de uma **interface**, pois não há reuso na relação entre a classe "filha" e as interfaces. Não é herança e sim cumprimento de contrato.

### Aula 234. Interface Comparable

Implementação básica:

```java
public interface Comparable<T> {
	int compareTo(T o);
}	
```


**Problema motivador**
> Faça um programa para ler um arquivo contendo nomes de pessoas (um nome por linha), armazenando-os em uma lista. Depois, ordenar os dados dessa lista e mostrá-los ordenadamente na tela. *Nota*: o caminho do arquivo pode ser informado "hardcode".


Forma de ordenar uma coleção: `Collections.sort()`.

**Método `compareTo()`**: o método `compareTo()` serve para comparar dois objetos com base em um critério estabelecido. Existe uma interface chamada `Comparable` e ela deve ser implementada, bem como o método `compareTO()` deve ser sobrescrito com o seu critério de comparação. Na aula do curso do Nélio Alves, ficou da seguinte forma:

```java
package secao18.entities;

// implementing Comparable interface
public class Employee implements Comparable<Employee>{
    private String name;
    private Double salary;

    public Employee(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    // Metodo necessario para utilizar o metodo sort() de Collections
    @Override
    public int compareTo(Employee e) {
        return name.compareTo(e.getName());
    }
}

```

Na documentação a interface `Comparable` recebe um objeto genérico do tipo `T`: 
```java
package java.lang;

public interface Comparable<T> {
    int compareTo(T var1);
}
```

No nosso caso colocamos ao invés de `T` o objeto que queríamos comparar.


### Aula 235. Default methods

As interfaces podem conter métodos concretos (+Java 8). Os métodos padrão é prover implementação padrão para métodos, de modo a evitar repetição de implementação em toda classe que implemente a interface; assim como a necessidade de se criar classes abstratas para prover reuso da implementação.

**Problema exemplo:**
> Fazer um programa para ler uma quantia e a duração em meses de um empréstimo. Informar o valor a ser pago depois de decorrido o prazo do empréstimo, conforme regras de juros do Brasil. A regra de cálculo de juros do Brasil é juro composto padrão de 2% ao mês.

A modelagem matemática deste problema é:
q * 1.02^{n} 

em que:

 q : saldo;

1.02 : o saldo acrescido de 2%;

n : prazo de pagamento, quantidade de vezes.

Para ilustrar essa aula, criamos uma classe chamada `BrazilInterestService` no package `services` em que ela é definida desta maneira:

```java
package secao18.services;

import java.security.InvalidParameterException;

public class BrazilInterestService {
    private double interestRate;

    public BrazilInterestService(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double payment(double amount, int months) {
        if (months < 1) {
            throw new InvalidParameterException("Months must be greater than zero");
        }
        return amount * Math.pow(1.0+interestRate/100.00, months);
    }
}
```

Ou seja, ela tem como atributo um double chamado `interestRate` que define a **taxa de juros**. Como especificado pelo problema,
o nosso `interestRate` é de 2%.

Para testar, dentro do package `application` existe a classe `Aula234`:

```java
package secao18.application;

import secao18.services.BrazilInterestService;

import java.util.Locale;
import java.util.Scanner;

public class Aula235 {
    public static void main(String[] args) {

      Locale.setDefault(Locale.US);
      Scanner sc = new Scanner(System.in);

      BrazilInterestService bis = new BrazilInterestService(2.0);
      System.out.print("Amount: ");
      double amount = sc.nextDouble();
      System.out.print("How many months: ");
      int months = sc.nextInt();

      System.out.printf("Brazil Payment: R$ %.2f", bis.payment(amount, months));

      sc.close();
    }
}
```

Depois criamos a classe `UsaInterestService` com o mesmo escopo de `BrazilInterestService`, porém considerando o `interestRate`
de 1%. E aí a classe principal, `Aula234`, ficou desta maneira:

```java
package secao18.application;

import secao18.services.BrazilInterestService;
import secao18.services.UsaInterestService;

import java.util.Locale;
import java.util.Scanner;

public class Aula235 {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        BrazilInterestService bis = new BrazilInterestService(2.0);
        System.out.print("Amount: ");
        double amount = sc.nextDouble();
        System.out.print("How many months: ");
        int months = sc.nextInt();

        System.out.printf("Brazil Payment: R$ %.2f", bis.payment(amount, months));

        UsaInterestService uis = new UsaInterestService(1.0);
        System.out.printf("%nUSA Payment: $ %.2f", uis.payment(amount, months));


        sc.close();
    }
}
```

Vamos refatorar o nosso código criando uma interface chamada `InterestService` que implementará dois métodos:
`getInterestRate()` (lembre-se que é a taxa de juros) e o método `payment()` que é quem de fato calculará o pagamento.

Agora nas classes criadas anteriormente, vamos simplesmente acrescentar um `... implements InterestService` e colocar uma *annotation*
`@Override` acima dos métodos `getInterestRate()` e `payment()` das respectivas classes. Já no programa principal, deixaremos
as instâncias `bis` e `uis` como do tipo `InterestService`, porém instanciando `BrazilInterestService` e `UsaInterestService` respectivamente.

O objetivo desta aula é chegar até a seguinte refatoração:

Classe `InterestService`:
```java
package secao18.services;

import java.security.InvalidParameterException;

public interface InterestService {

    double getInterestRate();

    default double payment(double amount, int months) {
        if (months < 1) {
            throw new InvalidParameterException("Months must be greater than zero");
        }

        return amount * Math.pow(1+getInterestRate()/100.00, months);
    }
}
```

Classe `BrazilInterestService`:
```java
package secao18.services;

import java.security.InvalidParameterException;

public class BrazilInterestService implements InterestService {
    private double interestRate;

    public BrazilInterestService(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

}
```

Classe `UsaInterestService`:

```java
package secao18.services;

import java.security.InvalidParameterException;

public class UsaInterestService implements InterestService{

    private double interestRate;

    public UsaInterestService(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public double getInterestRate() {
        return this.interestRate;
    }

}
```

Veja que deixamos o método `payment()` como default na interface. Apenas deixamos o método `getInterestRate()` em cada uma das classes,
pois ele terá valores distintos para cada implementação.

Esta é uma forma de se ter **herança múltipla** no Java e as interfaces provém **reuso**.

> Interfaces são bem diferentes das classes **abstratas**. As interfaces não possuem recursos como construtores e atributos.


# Seção 19: Generics, Set e Map

### Aula 238. Introdução aos `Generics`

`Generics` permitem que **classes**, **interfaces** e **métodos** possam ser parametrizados por tipo. Os seus benefícios são:
- Reuso;
- Type Safety;
- Performance.

É muito usado em `Collections`.

```java
List<String> list = new ArrayList<>();
list.add("Maria");
String name = list.get(0);
```

**Problema motivador:**
> Deseja-se fazer um programa que leia uma quantidade N, e depois N números inteiros. 
> Ao final, imprima esses números de forma organizada conforme exemplo. 
> Em seguida, informar qual foi o primeiro valor informado.

Vamos criar um serviço de impressão chamado `PrintService`.

No package `secao19.service` foi criado a classe `PrintService` desta forma:

```java
package secao19.service;

import java.util.ArrayList;
import java.util.List;

public class PrintService {
    private List<Integer> lista = new ArrayList<>();

    public void addValue(Integer value) {
        lista.add(value);
    }

    public Integer first() {
        if (lista.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return lista.get(0);
    }

    public void print() {
        System.out.print("[");

        if (!lista.isEmpty()) {
            System.out.print(lista.get(0));
        }
        for (int i=1; i<lista.size(); i++) {
            System.out.println(", " + lista.get(i));
        }

    }
}
```

e no package `secao19.application` criamos a classe `Aula238` instanciando o `PrintService`.

Porém da forma acima, se tentarmos colocar um dado na lista do tipo `String` ele dará erro.

Uma eventual solução para isso é mudar o tipo de `List<>` para o tipo `Object`. No Java, **tudo é `Object`**.

O problema de deixar como `Object` é que qualquer valor passado ele irá aceitar. Suponhamos que, no fim das contas, 
queiramos que o nosso array seja apenas de inteiros. Se por alguma razão, adicionar antes uma string ele deixará passar.

O problema acima pode ser solucionado com generics.

A classe `PrintService` utilizando generics fica da seguinte forma:

```java
package secao19.service;

import java.util.ArrayList;
import java.util.List;

public class PrintService<T> {
    // List<T> agora é uma lista de tipo genérico
    private List<T> lista = new ArrayList<>();

    public void addValue(T value) {
        lista.add(value);
    }

    public T first() {
        if (lista.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return lista.get(0);
    }

    public void print() {
        System.out.print("[");

        if (!lista.isEmpty()) {
            System.out.print(lista.get(0));
        }
        for (int i=1; i<lista.size(); i++) {
            System.out.print(", " + lista.get(i));
        }
        System.out.print("]\n");

    }
}
```

### Aula 239: Generics delimitados

**Problema motivador:**
> Uma empresa de consultoria deseja avaliar a performance de produtos, funcionários, dentre outras coisas. Um dos cálculos que ela precisa é encontrar 
> o maior dentre um conjunto de elementos. Fazer um programa que leia um 
> conjunto de produtos a partir de um arquivo, conforme exemplo, e depois mostre o mais caro deles.

Quando se trata de um método estático, não é necessário instanciar a classe.

Lógica para implementar a busca do maior número inteiro dentro de um array:

```java
package secao19.service;

import java.util.Arrays;
import java.util.List;

public class CalculationService {

    public static Integer max(List<Integer> list) {

        // Logica defensiva
        if (list.isEmpty()) {
            throw new IllegalStateException("List can't be empty");
        }

        Integer max = list.get(0);
        for (Integer item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }
}
```

O problema motivador indica que queremos encontrar o maior em uma lista de **produtos**.

Para ler e armazenar em uma lista os dados de uma entidade, que está no formato CSV, podemos fazer:

```java
String[] fields = line.split(",");
productList.add(new Product(fields[0], Double.parseDouble(fields[1])));
```

Uma alteração que precisa ser feita, é que a classe `Product` deve ser um subtipo do módulo `Comparable` e também precisa implementar o método `compareTo`.

Desta forma, o código completo da classe `Product` é:

```java
package secao19.entities;

// Implements Comparable<tipo>
public class Product implements Comparable<Product> {
    private String name;
    private Double price;

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ", " + String.format("%.2f", price);
    }

    // método de COMPARABLE
    @Override
    public int compareTo(Product product) {
        return price.compareTo(product.getPrice());
    }
}

```

Apesar de o código funcionar da forma que está, a forma mais **correta** é:

```java
package secao19.service;

import java.util.List;

public class CalculationService {

    public static <T extends Comparable<? super T>> T max(List<T> list) {

        // Logica defensiva
        if (list.isEmpty()) {
            throw new IllegalStateException("List can't be empty");
        }

        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }

        return max;
    }
}

```

>  O método estático `max` é de um tipo genérico T, que implementa o `Comparable` para `T` e toda superclasse `T`.

### Aula 240. Tipos curinga

Generics são **invariantes**.

De acordo com a [documentação oficial](https://docs.oracle.com/javase/8/docs/api/java/lang/Object.html), a classe `Object` é a raiz da hierarquia de classes. 

> Todos os objectos, incluindo arrays, implementam os métodos desta classe.

Agora considere `List<Object>`. Trata-se de uma lista do tipo `Object`.

Sabe-se que a classe `Integer` é subtipo da classe `Object`. **Será que `List<Integer>` é um subtipo de `List<Object>`?**

```java
List<Object> myObj = new ArrayList<Object>();

List<Integer> myInt = new ArrayList<Integer>();

myObj = myInt;      // ERRO DE COMPILAÇÃO
```

O erro estourado pelo código acima é:

> java: incomparable types: java.util.List<java.lang.Object> and java.util.List<java.lang.Integer>

Para o caso de `List<>`, o supertipo é:

```java
List<?>
```


Readaptando o código de cima:

```java
List<?> myObj = new ArrayList<Object>();
List<Integer> myInt = new ArrayList<Integer>();

myObj = myInt;      // FUNCIONA
```

O retorno do código acima é:

> []

Com tipos curinga podemos fazer métodos que recebem um genérico de qualquer tipo.

Um exemplo prático é quando criamos uma interface que criará um contrato para as classes que implementarem-no. Exemplo:

```java
public interface ICrudService<T> {
    Person findPersonById(Long id);
    List<Person> findAll();
}
```

Outro exemplo:

```java
package secao19.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Aula240 {

    public static void main(String[] args) {

        List<Integer> myInts = Arrays.asList(2, 4, 6);
        printList(myInts);

        List<String> myStrs = Arrays.asList("Java", "&", "Spring Boot");
        printList(myStrs);
    }

    public static void printList(List<?> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}

```

Porém, **não é possível adicionar** elementos a uma coleção de tipo curinga.

### Aula 241: Curingas delimitados

#### PROBLEMA 1:

> Método que retorne a soma das áreas de uma lista de figuras.

**Nota 1:** soluções impróprias

```java
public double totalArea(List<Shape> list) {}

public double totalArea(List<?> list) {}
```

O problema proposto pelo professor incluía criar uma lista de `Shapes`, em que cada shape poderia ser um `Circle` ou um `Rectangule`. A lista criada ficou no formato: 

[

​	rectangule(3.0, 2.0),

​	circle(3.0)

]



Foi criado um método `totalArea` em que recebia como parâmetro uma lista de shapes. Este método é responsável por pegar as áreas de cada figura e somá-las iterando sobre a lista previamente criada e passada como parâmetro.

O problema começa quando se cria uma lista de `Circle` ou de `Rectangule`. Ao chamar o método `totalArea`, que recebe uma **lista de shape**, ele não necessariamente conseguirá implementar para um **subtipo** de shape. Veja:

```java
public static double totalArea(List<Shape> list) {
    // ...
}
```

Quando criado uma lista de `Circle` o método `totalArea` não funcionará. Para funcionar, precisa colocar um **tipo curinga**. Se substituirmos para:

```java
public static double totalArea(List<?> list) {
    // ...
}
```

o compilador reclamará que não necessariamente os elementos da lista serão do tipo `Shape`. O erro ao executar o método da forma que está acima é:

> java: incompatible types: capture#1 of ? cannot be converted to secao19.entities.Shape

**O que queremos:** o parâmetro do método deve ser do tipo `Shape` incluindo os subtipos de shape.

**Como resolvemos:**

```java
public static double totalArea(List<? extends Shape> list) {
    // ...
}
```

A lista acima pode ser `Shape` ou **qualquer subtipo de Shape**.

#### PROBLEMA 2 (princípio get/put - covariância):

> Vamos fazer um método que **copia** os elementos de uma lista para uma **outra** lista que pode ser **mais genérica** que a primeira.

Suponha o seguinte código:

```java
List<Integer> intList = new ArrayList<Integer>();
intList.add(10);
intList.add(5);

List<? extends Number> list = intList;		// criou uma cópia de intList com um supertipo

Number x = list.get(0);

list.add(20);		// erro de compilação
```

Dará erro para adicionar, pois o compilador não tem como saber que o valor que tenta ser adicionado é compatível com algum outro possível tipo `Number` que possa ser a lista.

O erro de compilação é:

> java: incompatible types: int cannot be converted to capture#1 of ? extends java.lang.Number

**Covariância:**

> Quando a opção `get` é permitida e a `put` **não**.

**Contravariância:**

> Quando a opção `put` é permitida e a `get` **não**.

O código de exemplo para trazer a covariância:

```java
        List<Object> myObjs = new ArrayList<Object>();
        myObjs.add("Bea");
        myObjs.add("Ana");

        List<? super Number> myNums = myObjs;
        myNums.add(10);
        myNums.add(3.14);

        Number x = myNums.get(0);		// erro de compilação
```

O erro de compilação é:

> java: incompatible types: capture#1 of ? super java.lang.Number cannot be converted to java.lang.Number

Nota-se que, no código da variância, existe o `? extends Number` e no da covariância, `? super Number`. **O que significa?**

O primeiro diz que é para qualquer Number e todo **subtipo de Number**. O segundo quer dizer que é para qualquer Number e todo **supertipo de Number**.

### Aula 242. HashCode e Equals

São operações da classe `Object` utilizadas para comparar se um objeto é **igual** a outro.

- **Equals:** lento, resposta 100%.
- **Hashcode:** rápido, porém a resposta positiva não é 100% (falso positivo).

**Regra de ouro do Hashcode:**

> Se o hashCode de dois objetos for diferente, então os dois objetos são diferentes.

Isso **não** quer dizer que: dois objetos só são iguais, se o hashCode deles for o mesmo.

Tipos comuns já possuem a implementação para essas operações como: String, Date, Integer, Double, etc.

Classes personalizadas precisam sobrepô-las.

**Exemplo:**

```java
String nomeDaMae = "Rita";
String nomeDoPai = "Sérgio";

boolean ehTrue = nomeDaMae.equals(nomeDoPai);		// false
```

```java
String nomeDoIrmao = "Tadeu";		// hashcode = 80564839
String nomeDaIrma = "Cássia";		// hashcode = 2129485174

boolean hashCodeEhOMesmo = (nomeDaIrma.hashCode()) == (nomeDoIrmao.hashCode());		// false
```

Criamos uma classe `Client`, dentro do package `entities`, em que ficou como:

```java
package secao19.entities;

public class Client {
    private String name;
    private String email;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return name.equals(client.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

```

A implementação do método `equals()` e `hashCode()` acima comparam apenas o atributo **nome**, porém, um nome não é único.

Dado os atributos que temos, `name` e `email`, o mais recomendado é considerar que cada pessoa é formada (dentro do escopo do projeto) por nome **e** e-mail únicos.

Deste modo, as novas implementações dos métodos supracitados são:

```java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!name.equals(client.name)) return false;
        return email.equals(client.email);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
```

***Qual a diferença entre `equals` e `==`***?

**R.:** o `==` **não** compara o **conteúdo** e sim o **endereço de memória**, a **referência**. O primeiro compara o **conteúdo**.

Para os tipos `String`, o compilador trata diferentemente. Se fizermos:

```java
String s1 = "teste";
String s2 = "teste";

boolean ehTrue = s1 == s2;		// true
```

Para **forçar** a criação de um objeto `String`, deveria ser:

```java
String s1 = new String("teste");
String s2 = new String("teste");
```

### Aula 243. `Set<T>`

`Set<T>` é um conjunto algébrico de elementos, em que operações como **intersecção**, **união** e **diferença** existem. É importante destacar que esta interface **não admite repetições**, os elementos **não possuem posição** e o **acesso**, **inserção** e **remoção** de elementos são rápidos.

As principais implementações são:

- **HashSet:** operações $O(1)$ em tabela hash e **não ordenado**.
- **TreeSet:** operações $O(log n)$ em árvore rubro-negra e ordenado pelo `compareTo()` do objeto (ou `Comparator`).
- **LinkedHashSet:** velocidade intermediária (> TreeSet) e elementos na ordem em que são adicionados.

**_Nota_:** *como o tempo é constante em O(1), este tempo de execução é mais rápido que O(log n), visto que este último varia conforme entram dados. Já o LinkedHashSet consegue ser mais rápido que o TreeSet, pois para inserção, remoção e busca o tempo de execução dele é O(1), apenas na iteração que é O(n) (mais lento que O(log n)).*

#### Alguns métodos importantes

- `add(obj)`, `remove(obj)`, `contains(obj)`
  - baseado em **equals** e **hashCode**
  - se equals e hashCode **não** existir, é utilizado **comparação de ponteiros**.
- `clear()`
- `size()`
- `removeIf(predicate)`
- `addAll(other)` -  **união:**
- `retainAll(other)`- **intersecção:**
- `removeAll(other)` - **diferença:**

### Seções extras

#### DevDojo - Aula 98: IO pt 01 Classe File para arquivos

