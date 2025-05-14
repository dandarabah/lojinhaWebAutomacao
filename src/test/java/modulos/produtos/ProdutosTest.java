package modulos.produtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web do Modulo de Produtos")
public class ProdutosTest {
    @Test
    @DisplayName("Nao e permitido regitrar um produto com valor igual a zero")
    public void testNaoEPermitoRegistrarProdutoComValorIgualAZero() {
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver-win64\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        //Vou maximizar a tela
        navegador.manage().window().maximize();

        //Vou definir um tempo de espera padrao de 5 segundos
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a pagina da Lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web-bugada/v2");

        //Fazer o login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");

        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");

        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        //Vou para a tela de registro de produto
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //Vou preencher dados do produto e o valor sera igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys("Tablet");
        navegador.findElement(By.name("produtovalor")).sendKeys("140000");
        navegador.findElement(By.id("produtocores")).sendKeys("preto, branco, rosa");

        //Vou submeter o formulario
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        //Vou validar que a mensagem de erro foi apresentado
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);
    }
}
