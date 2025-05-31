package paginas;

import net.bytebuddy.utility.AsmClassWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicionarComponentePage {
    private WebDriver navegador;

    public FormularioAdicionarComponentePage (WebDriver navegador){
        this.navegador = navegador;
    }


    public FormularioAdicionarComponentePage informarNomeDoComponenteProduto(String produtoNomeComponente){
        navegador.findElement(By.id("componentenomeadicionar")).sendKeys(produtoNomeComponente);
        return this;
    }

    public FormularioAdicionarComponentePage informarQuantidadeDeComponentesProduto(String produtoQuantidadeComponente){
        navegador.findElement(By.id("componentequantidadeadicionar")).sendKeys(produtoQuantidadeComponente);
        return this;
    }

    public FormularioDeEditarProdutoPage submeterFormularioDeEdicaoComponente(){
        navegador.findElement(By.xpath("//*[@id=\"novocomponente\"]/div/div[4]/a[1]")).click();
        return new FormularioDeEditarProdutoPage(navegador);
    }

}
