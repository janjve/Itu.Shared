/**
 */
package mdsebook.fsm.impl;

import java.util.Collection;

import mdsebook.fsm.FiniteStateMachine;
import mdsebook.fsm.FsmPackage;
import mdsebook.fsm.State;
import mdsebook.fsm.Transition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.fsm.impl.StateImpl#getLeavingTransitions <em>Leaving Transitions</em>}</li>
 *   <li>{@link mdsebook.fsm.impl.StateImpl#getMachine <em>Machine</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StateImpl extends NamedElementImpl implements State {
	/**
	 * The cached value of the '{@link #getLeavingTransitions() <em>Leaving Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLeavingTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Transition> leavingTransitions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FsmPackage.Literals.STATE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Transition> getLeavingTransitions() {
		if (leavingTransitions == null) {
			leavingTransitions = new EObjectContainmentWithInverseEList<Transition>(Transition.class, this, FsmPackage.STATE__LEAVING_TRANSITIONS, FsmPackage.TRANSITION__SOURCE);
		}
		return leavingTransitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FiniteStateMachine getMachine() {
		if (eContainerFeatureID() != FsmPackage.STATE__MACHINE) return null;
		return (FiniteStateMachine)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMachine(FiniteStateMachine newMachine, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMachine, FsmPackage.STATE__MACHINE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMachine(FiniteStateMachine newMachine) {
		if (newMachine != eInternalContainer() || (eContainerFeatureID() != FsmPackage.STATE__MACHINE && newMachine != null)) {
			if (EcoreUtil.isAncestor(this, newMachine))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMachine != null)
				msgs = ((InternalEObject)newMachine).eInverseAdd(this, FsmPackage.FINITE_STATE_MACHINE__STATES, FiniteStateMachine.class, msgs);
			msgs = basicSetMachine(newMachine, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FsmPackage.STATE__MACHINE, newMachine, newMachine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FsmPackage.STATE__LEAVING_TRANSITIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getLeavingTransitions()).basicAdd(otherEnd, msgs);
			case FsmPackage.STATE__MACHINE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMachine((FiniteStateMachine)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FsmPackage.STATE__LEAVING_TRANSITIONS:
				return ((InternalEList<?>)getLeavingTransitions()).basicRemove(otherEnd, msgs);
			case FsmPackage.STATE__MACHINE:
				return basicSetMachine(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case FsmPackage.STATE__MACHINE:
				return eInternalContainer().eInverseRemove(this, FsmPackage.FINITE_STATE_MACHINE__STATES, FiniteStateMachine.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FsmPackage.STATE__LEAVING_TRANSITIONS:
				return getLeavingTransitions();
			case FsmPackage.STATE__MACHINE:
				return getMachine();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case FsmPackage.STATE__LEAVING_TRANSITIONS:
				getLeavingTransitions().clear();
				getLeavingTransitions().addAll((Collection<? extends Transition>)newValue);
				return;
			case FsmPackage.STATE__MACHINE:
				setMachine((FiniteStateMachine)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case FsmPackage.STATE__LEAVING_TRANSITIONS:
				getLeavingTransitions().clear();
				return;
			case FsmPackage.STATE__MACHINE:
				setMachine((FiniteStateMachine)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case FsmPackage.STATE__LEAVING_TRANSITIONS:
				return leavingTransitions != null && !leavingTransitions.isEmpty();
			case FsmPackage.STATE__MACHINE:
				return getMachine() != null;
		}
		return super.eIsSet(featureID);
	}

} //StateImpl
