import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	// Función para validar etiquetas HTML

	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
		Stack<HtmlTag> stack = new Stack<>();

		for (HtmlTag tag : tags) {
			// Si es una etiqueta auto-cerrada, se ignora
			if (tag.isSelfClosing()) {
				// Ignorar etiquetas auto-cerradas
				continue;
			} else if (tag.isOpenTag()) {
				// Si es una etiqueta de apertura, se agrega a la pila
				stack.push(tag);
			} else if (!tag.isOpenTag()) {

				// Es una etiqueta de cierre
				if (!stack.isEmpty()) {
					// Verifica si la etiqueta de cierre coincide con la etiqueta superior en la pila
					if (stack.peek().matches(tag)) {
						stack.pop(); //  La etiqueta coincide, la eliminamos de la pila
					} else {
						return stack; // La etiqueta de cierre no coincide, devolvemos la pila actual
					}
				} else {
					return null; // La etiqueta de cierre sin etiqueta de apertura correspondiente, devuelve null
				}
			}
		}

		return stack.isEmpty() ? new Stack<>() : stack; // Al final del proceso, verifica si hay etiquetas de apertura no cerradas
	}
}
