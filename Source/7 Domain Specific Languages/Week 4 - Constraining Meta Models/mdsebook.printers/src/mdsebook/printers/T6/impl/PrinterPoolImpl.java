/**
 */
package mdsebook.printers.T6.impl;

import java.util.Collection;

import mdsebook.printers.T6.Printer;
import mdsebook.printers.T6.PrinterPool;
import mdsebook.printers.T6.Scanner;
import mdsebook.printers.T6.T6Package;

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
 *   <li>{@link mdsebook.printers.T6.impl.PrinterPoolImpl#getPrinter <em>Printer</em>}</li>
 *   <li>{@link mdsebook.printers.T6.impl.PrinterPoolImpl#getScanner <em>Scanner</em>}</li>
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
	 * The cached value of the '{@link #getScanner() <em>Scanner</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScanner()
	 * @generated
	 * @ordered
	 */
	protected EList<Scanner> scanner;

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
		return T6Package.Literals.PRINTER_POOL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Printer> getPrinter() {
		if (printer == null) {
			printer = new EObjectContainmentWithInverseEList<Printer>(Printer.class, this, T6Package.PRINTER_POOL__PRINTER, T6Package.PRINTER__POOL);
		}
		return printer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Scanner> getScanner() {
		if (scanner == null) {
			scanner = new EObjectContainmentWithInverseEList<Scanner>(Scanner.class, this, T6Package.PRINTER_POOL__SCANNER, T6Package.SCANNER__POOL);
		}
		return scanner;
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
			case T6Package.PRINTER_POOL__PRINTER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPrinter()).basicAdd(otherEnd, msgs);
			case T6Package.PRINTER_POOL__SCANNER:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getScanner()).basicAdd(otherEnd, msgs);
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
			case T6Package.PRINTER_POOL__PRINTER:
				return ((InternalEList<?>)getPrinter()).basicRemove(otherEnd, msgs);
			case T6Package.PRINTER_POOL__SCANNER:
				return ((InternalEList<?>)getScanner()).basicRemove(otherEnd, msgs);
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
			case T6Package.PRINTER_POOL__PRINTER:
				return getPrinter();
			case T6Package.PRINTER_POOL__SCANNER:
				return getScanner();
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
			case T6Package.PRINTER_POOL__PRINTER:
				getPrinter().clear();
				getPrinter().addAll((Collection<? extends Printer>)newValue);
				return;
			case T6Package.PRINTER_POOL__SCANNER:
				getScanner().clear();
				getScanner().addAll((Collection<? extends Scanner>)newValue);
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
			case T6Package.PRINTER_POOL__PRINTER:
				getPrinter().clear();
				return;
			case T6Package.PRINTER_POOL__SCANNER:
				getScanner().clear();
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
			case T6Package.PRINTER_POOL__PRINTER:
				return printer != null && !printer.isEmpty();
			case T6Package.PRINTER_POOL__SCANNER:
				return scanner != null && !scanner.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //PrinterPoolImpl
