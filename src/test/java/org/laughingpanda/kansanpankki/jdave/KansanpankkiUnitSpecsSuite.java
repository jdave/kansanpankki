package org.laughingpanda.kansanpankki.jdave;

import jdave.junit4.JDaveGroupRunner;
import jdave.runner.Groups;
import org.junit.runner.RunWith;

/**
 * @author Marko Sibakov / Reaktor Innovations Oy
 */
@RunWith(JDaveGroupRunner.class)
@Groups(include = Groups.ALL, exclude = { "kansanpankki" })
public class KansanpankkiUnitSpecsSuite {
}