package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RemoverProdutoDaListaPage {
    protected WebDriver navegador;

    public RemoverProdutoDaListaPage(WebDriver navegador){
        this.navegador = navegador;
    }
    public RemoverProdutoDaListaPage excluirUmProdutoDaLista(String produtoNome) {
        navegador.findElement(By.xpath("/html/body/div[2]/div/ul/li[89]/a/i")).sendKeys(produtoNome);
    return this;
    }
}
