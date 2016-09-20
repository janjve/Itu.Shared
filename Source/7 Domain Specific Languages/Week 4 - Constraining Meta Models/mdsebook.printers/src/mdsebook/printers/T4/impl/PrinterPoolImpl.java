/**
 */
package mdsebook.printers.T4.impl;

import java.util.Collection;

import mdsebook.printers.T4.Printer;
import mdsebook.printers.T4.PrinterPool;
import mdsebook.printers.T4.T4Package;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Printer Pool</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T4.impl.PrinterPoolImpl#getPrinter <em>Printer</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrinterPoolImpl extends MinimalEObjectImpl.Container implements PrinterPool {
	/**
	 * The cached value of the '{@link #getPrinter() <em>Printer</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrinter()
	 * @generated
	 * @ordered
	 */
	protected EList<Printer> printer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PrinterPoolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return T4Package.Literals.PRINTER_POOL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Printer> getPrinter() {
		if (printer == null) {
			printer = new EObjectContainmentWithInverseEList<Printer>(Printer.class, this, T4Package.PRINTER_POOL__PRINTER, T4Package.PRINTER__POOL);
		}
		return printer;
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
			case T4Package.PRINTER_POOL__PRINTER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPrinter()).basicAdd(otherEnd, msgs);
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
			case T4Package.PRINTER_POOL__PRINTER:
				return ((InternalEList<?>)getPrinter()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case T4Package.PRINTER_POOL__PRINTER:
				return getPrinter();
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
			case T4Package.PRINTER_POOL__PRINTER:
				getPrinter().clear();
				getPrinter().addAll((Collection<? extends Printer>)newValue);
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
			case T4Package.PRINTER_POOL__PRINTER:
				getPrinter().clear();
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
			case T4Package.PRINTER_POOL__PRINTER:
				return printer != null && !printer.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PrinterPoolImpl
