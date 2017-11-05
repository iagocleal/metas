package br.com.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplication extends Application{
	private Set<Object> singletons = new HashSet<Object>();

	public RestApplication() {
		singletons.add(new MetaService());
		singletons.add(new OrgaoService());
		singletons.add(new SetorService());
		singletons.add(new TipoMetaService());			
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
