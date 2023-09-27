import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {

    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
        /* IMPLEMENT THIS METHOD! */
        Stack<HtmlTag> tagStack = new Stack<>();

        // Itera a través de las etiquetas en la cola hasta que esté vacía
        while (!tags.isEmpty()) {
            HtmlTag tag = tags.poll();

            // Verifica si la etiqueta no es auto-cierre
            if (!tag.isSelfClosing()) {
                if (tag.isOpenTag()) {
                    // Es una etiqueta de apertura, la agregamos a la pila
                    tagStack.push(tag);
                } else {
                    // Es una etiqueta de cierre
                    if (tagStack.isEmpty()) {
                        // Si la pila está vacía y encontramos una etiqueta de cierre, el HTML no es válido
                        return null;
                    }
                    // Compara la etiqueta de cierre con la etiqueta en la cima de la pila
                    if (tagStack.peek().matches(tag)) {
                        // Si coinciden, la etiqueta de apertura correspondiente se retira de la pila
                        tagStack.pop();
                    } else {
                        // Si no coinciden, el HTML no es válido
                        return tagStack;
                    }
                }
            }
        }

        // Verifica si quedan etiquetas de apertura sin cerrar
        if (!tagStack.isEmpty()) {
            // Si la pila no está vacía al final, significa que hay etiquetas de apertura sin cerrar
            return tagStack;
        }

        // Si no se encontraron problemas de emparejamiento de etiquetas, se devuelve una pila vacía
        return new Stack<>();
    }

}

