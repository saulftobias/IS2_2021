package es.unican.is2.practica4;

/**
 * TDA ListaOrdenada 
 * Estructura de datos que almacena los datos ordenados de acuerdo
 * a su orden natural
 * 
 * @param <E> tipo de los elementos almacenados en la lista
 */
public interface IListaOrdenada<E> {

	/**
	 * Retorna el elemento que ocupa la posici�n indicada
	 * @param indice Indice del elemento al que se accede
	 * @return El elemento en la posici�n indice
	 * @throws IndexOutOfBoundsException si el �ndice es incorrecto
	 */
	public E get(int indice) throws IndexOutOfBoundsException;

	/**
	 * A�ade el elemento que se pasa como par�metro a la posici�n
	 * que le corresponda por orden natural
	 * @param elemento Elemento a a�adir
	 */
	public void add(E elemento);

	/**
	 * Elimina el elemento que ocupa la posici�n indicada
	 * @param indice Indice del elemento que se quiere eliminar
	 * @return El elemento eliminado
	 * @throws IndexOutOfBoundsException si el �ndice es incorrecto
	 */
	public E remove(int indice) throws IndexOutOfBoundsException;

	/**
	 * Retorna el tama�o de la lista
	 */
	public int size();

	/**
	 * Elimina todos los elemnentos de la lista
	 */
	public void clear();

}
