/**
 */
package mdsebook.printers.T5.impl;

import java.util.Collection;

import mdsebook.printers.T5.Printer;
import mdsebook.printers.T5.PrinterPool;
import mdsebook.printers.T5.T5Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
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
 *   <li>{@link mdsebook.printers.T5.impl.PrinterPoolImpl#getPrinter <em>Printer</em>}</li>
 *   <li>{@link mdsebook.printers.T5.impl.PrinterPoolImpl#isColor <em>Color</em>}</li>
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
	 * The default value of the '{@link #isColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isColor()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLOR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isColor()
	 * @generated
	 * @ordered
	 */
	protected boolean color = COLOR_EDEFAULT;

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
		return T5Package.Literals.PRINTER_POOL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Printer> getPrinter() {
		if (printer == null) {
			printer = new EObjectContainmentWithInverseEList<Printer>(Printer.class, this, T5Package.PRINTER_POOL__PRINTER, T5Package.PRINTER__POOL);
		}
		return printer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isColor() {
		return color;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColor(boolean newColor) {
		boolean oldColor = color;
		color = newColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T5Package.PRINTER_POOL__COLOR, oldColor, color));
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
			case T5Package.PRINTER_POOL__PRINTER:
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
			case T5Package.PRINTER_POOL__PRINTER:
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
			case T5Package.PRINTER_POOL__PRINTER:
				return getPrinter();
			case T5Package.PRINTER_POOL__COLOR:
				return isColor();
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
			case T5Package.PRINTER_POOL__PRINTER:
				getPrinter().clear();
				getPrinter().addAll((Collection<? extends Printer>)newValue);
				return;
			case T5Package.PRINTER_POOL__COLOR:
				setColor((Boolean)newValue);
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
			case T5Package.PRINTER_POOL__PRINTER:
				getPrinter().clear();
				return;
			case T5Package.PRINTER_POOL__COLOR:
				setColor(COLOR_EDEFAULT);
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
			case T5Package.PRINTER_POOL__PRINTER:
				return printer != null && !printer.isEmpty();
			case T5Package.PRINTER_POOL__COLOR:
				return color != COLOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (color: ");
		result.append(color);
		result.append(')');
		return result.toString();
	}

} //PrinterPoolImpl
