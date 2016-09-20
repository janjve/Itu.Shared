/**
 */
package mdsebook.fsm;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Finite State Machine</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.fsm.FiniteStateMachine#getStates <em>States</em>}</li>
 *   <li>{@link mdsebook.fsm.FiniteStateMachine#getInitial <em>Initial</em>}</li>
 * </ul>
 *
 * @see mdsebook.fsm.FsmPackage#getFiniteStateMachine()
 * @model
 * @generated
 */
public interface FiniteStateMachine extends NamedElement {
	/**
	 * Returns the value of the '<em><b>States</b></em>' containment reference list.
	 * The list contents are of type {@link mdsebook.fsm.State}.
	 * It is bidirectional and its opposite is '{@link mdsebook.fsm.State#getMachine <em>Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>States</em>' containment reference list.
	 * @see mdsebook.fsm.FsmPackage#getFiniteStateMachine_States()
	 * @see mdsebook.fsm.State#getMachine
	 * @model opposite="machine" containment="true" required="true"
	 * @generated
	 */
	EList<State> getStates();

	/**
	 * Returns the value of the '<em><b>Initial</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial</em>' reference.
	 * @see #setInitial(State)
	 * @see mdsebook.fsm.FsmPackage#getFiniteStateMachine_Initial()
	 * @model required="true"
	 * @generated
	 */
	State getInitial();

	/**
	 * Sets the value of the '{@link mdsebook.fsm.FiniteStateMachine#getInitial <em>Initial</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial</em>' reference.
	 * @see #getInitial()
	 * @generated
	 */
	void setInitial(State value);

} // FiniteStateMachine
