package cursojava.jpahibernate.orm.modelocompras.conversores;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import cursojava.jpahibernate.orm.modelocompras.enums.Subscripcion;

@Converter
public class ConversorDeSubcripcion implements AttributeConverter<Subscripcion, Character> {

	@Override
	public Character convertToDatabaseColumn(Subscripcion attribute) {		
		return attribute.getCodigo();
	}

	@Override
	public Subscripcion convertToEntityAttribute(Character dbData) {
		
		if(dbData != null) {
			if(dbData == Subscripcion.ACTIVA.getCodigo()) {
				return Subscripcion.ACTIVA;
			}
			return Subscripcion.NO_ACTIVA;
		}
		
		return null;
	}

}
