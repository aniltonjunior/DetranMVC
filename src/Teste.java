import java.util.Vector;
import br.pucpr.trabalho5.Pessoa;
import br.pucpr.trabalho5.PessoaBO;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PessoaBO bo = new PessoaBO();
		
		bo.inserirPessoa(new Pessoa ("Jose", "Carlos", "00180859093"));
		
		mostraTodos(bo);
		
		bo.atualizarPessoa(null, 1);
		
		bo.removerPessoa(3);
		
		mostraTodos(bo);
	}
	
	public static void mostraTodos(PessoaBO bo) {
		Vector<Pessoa> resultado = bo.buscaPessoa("", 0);
		if (!resultado.isEmpty()) {
			for (Pessoa c: resultado) {
				System.out.println("Nome: " + c.getPreNome() + " " + c.getPosNome());	
			}
		}
	}

}
