/**
 */
package mdsebook.fsm;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.fsm.State#getLeavingTransitions <em>Leaving Transitions</em>}</li>
 *   <li>{@link mdsebook.fsm.State#getMachine <em>Machine</em>}</li>
 * </ul>
 *
 * @see mdsebook.fsm.FsmPackage#getState()
 * @model
 * @generated
 */
public interface State extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Leaving Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link mdsebook.fsm.Transition}.
	 * It is bidirectional and its opposite is '{@link mdsebook.fsm.Transition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Leaving Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Leaving Transitions</em>' containment reference list.
	 * @see mdsebook.fsm.FsmPackage#getState_LeavingTransitions()
	 * @see mdsebook.fsm.Transition#getSource
	 * @model opposite="source" containment="true"
	 * @generated
	 */
	EList<Transition> getLeavingTransitions();

	/**
	 * Returns the value of the '<em><b>Machine</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.fsm.FiniteStateMachine#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Machine</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Machine</em>' container reference.
	 * @see #setMachine(FiniteStateMachine)
	 * @see mdsebook.fsm.FsmPackage#getState_Machine()
	 * @see mdsebook.fsm.FiniteStateMachine#getStates
	 * @model opposite="states" required="true" transient="false"
	 * @generated
	 */
	FiniteStateMachine getMachine();

	/**
	 * Sets the value of the '{@link mdsebook.fsm.State#getMachine <em>Machine</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Machine</em>' container reference.
	 * @see #getMachine()
	 * @generated
	 */
	void setMachine(FiniteStateMachine value);

} // State
