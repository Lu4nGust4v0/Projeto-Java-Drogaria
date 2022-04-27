package br.com.luan.drogaria.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.luan.drogaria.util.HibernateUtil;

public class GenericDAO<Entidade> {
	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		Transaction transacao  = null;
		
		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();
		}catch(RuntimeException erro) {
			if(transacao != null){
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}
	}

}
