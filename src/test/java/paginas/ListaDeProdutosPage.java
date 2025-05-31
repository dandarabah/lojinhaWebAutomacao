package paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListaDeProdutosPage {
    protected WebDriver navegador;

    public ListaDeProdutosPage(WebDriver navegador) {
        this.navegador = navegador;
    }


    public FormularioDeAdicaoDeProdutoPage acessarFormularioDeAdicaoNovoProduto(){
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();
        return new FormularioDeAdicaoDeProdutoPage(navegador);
    }
    public FormularioDeEditarProdutoPage acessarFormularioDeEdicaoNovoProduto(String produtoNome) {
        navegador.findElement(By.xpath("/html/body/div[2]/div/ul/li[12]/span/a")).sendKeys(produtoNome);
        navegador.findElement(By.xpath("/html/body/div[2]/div/ul/li[12]/span/a")).click();
        return new FormularioDeEditarProdutoPage(navegador);
    }
    public RemoverProdutoDaListaPage getRemoverProdutoDaLista() {
        return new RemoverProdutoDaListaPage(navegador);
    }

    public String capturarMensagemApresentada(){
        return navegador.findElement(By.cssSelector(".toast.rounded")).getText();
    }

}
