/**
 */
package mdsebook.printers.T4.impl;

import mdsebook.printers.T4.ColorPrinterHead;
import mdsebook.printers.T4.Printer;
import mdsebook.printers.T4.T4Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Color Printer Head</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T4.impl.ColorPrinterHeadImpl#getPrinter <em>Printer</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ColorPrinterHeadImpl extends MinimalEObjectImpl.Container implements ColorPrinterHead {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColorPrinterHeadImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return T4Package.Literals.COLOR_PRINTER_HEAD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Printer getPrinter() {
		if (eContainerFeatureID() != T4Package.COLOR_PRINTER_HEAD__PRINTER) return null;
		return (Printer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrinter(Printer newPrinter, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPrinter, T4Package.COLOR_PRINTER_HEAD__PRINTER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrinter(Printer newPrinter) {
		if (newPrinter != eInternalContainer() || (eContainerFeatureID() != T4Package.COLOR_PRINTER_HEAD__PRINTER && newPrinter != null)) {
			if (EcoreUtil.isAncestor(this, newPrinter))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPrinter != null)
				msgs = ((InternalEObject)newPrinter).eInverseAdd(this, T4Package.PRINTER__HEAD, Printer.class, msgs);
			msgs = basicSetPrinter(newPrinter, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, T4Package.COLOR_PRINTER_HEAD__PRINTER, newPrinter, newPrinter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case T4Package.COLOR_PRINTER_HEAD__PRINTER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPrinter((Printer)otherEnd, msgs);
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
			case T4Package.COLOR_PRINTER_HEAD__PRINTER:
				return basicSetPrinter(null, msgs);
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
			case T4Package.COLOR_PRINTER_HEAD__PRINTER:
				return eInternalContainer().eInverseRemove(this, T4Package.PRINTER__HEAD, Printer.class, msgs);
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
			case T4Package.COLOR_PRINTER_HEAD__PRINTER:
				return getPrinter();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case T4Package.COLOR_PRINTER_HEAD__PRINTER:
				setPrinter((Printer)newValue);
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
			case T4Package.COLOR_PRINTER_HEAD__PRINTER:
				setPrinter((Printer)null);
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
			case T4Package.COLOR_PRINTER_HEAD__PRINTER:
				return getPrinter() != null;
		}
		return super.eIsSet(featureID);
	}

} //ColorPrinterHeadImpl
