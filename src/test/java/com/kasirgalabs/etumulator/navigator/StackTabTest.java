/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.navigator;

import com.kasirgalabs.etumulator.JavaFXThread;
import com.kasirgalabs.etumulator.document.BaseDocumentTest;
import com.kasirgalabs.etumulator.processor.Stack;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import org.junit.ClassRule;
import org.junit.Test;

public class StackTabTest {
    @ClassRule
    public static final JavaFXThread javaFXThread = new JavaFXThread();
    private final Stack stack;
    private final Navigator navigator;
    private final StackTab stackTab;

    public StackTabTest() throws IOException {
        assert Platform.isFxApplicationThread();

        stack = new Stack();
        navigator = new Navigator();
        stackTab = new StackTab(stack, navigator);
        ClassLoader classLoader = BaseDocumentTest.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/StackTab.fxml"));
        fxmlLoader.setControllerFactory((Class<?> param) -> {
            return stackTab;
        });
        fxmlLoader.load();
    }

    /**
     * Test of update method, of class StackTab.
     */
    @Test
    public void testUpdate() {
        assert Platform.isFxApplicationThread();

        final int RANDOM = (int) (Math.random() * Integer.MAX_VALUE);
        stack.notifyObservers("pop");
        stack.push(RANDOM);
        stack.push(RANDOM);
        stack.pop();
        navigator.notifyObservers();
    }
}