import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.pucpr.trabalho5.Pessoa;
import br.pucpr.trabalho5.PessoaBO;
import br.pucpr.trabalho5.Veiculo;
import br.pucpr.trabalho5.VeiculoBO;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*PessoaBO bo = new PessoaBO();
		
		bo.inserirPessoa(new Pessoa ("Jose", "Carlos", "00180859093"));
		
		mostraTodos(bo);
		
		bo.atualizarPessoa(null, 1);
		
		bo.removerPessoa(3);
		
		mostraTodos(bo);*/
		
		VeiculoBO bo = new VeiculoBO();
		
		//bo.inserirVeiculo(new Veiculo("BGD-3553", "Volkswagen", "Voyage", "1985",1));
		//bo.removerVeiculo(2);
		//SELECT a.preNome, a.posNome, b.modeloVeiculo, b.anoVeiculo FROM PESSOA a, VEICULO b WHERE a.id = b.propVeiculo
				
	}
	
	public static boolean verificaPlaca (String placaCompleta) {
		
		Pattern pattern = Pattern.compile("[a-zA-Z]{3}-?[0-9]{4}");
		Matcher matcher = pattern.matcher(placaCompleta);
		
		if(matcher.find()){
			return true;
		} else {
			return false;
		}
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
