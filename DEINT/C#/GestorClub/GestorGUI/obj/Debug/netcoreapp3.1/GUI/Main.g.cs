﻿#pragma checksum "..\..\..\..\GUI\Main.xaml" "{ff1816ec-aa5e-4d10-87f7-6f4963833460}" "FA816EFD093EAE3157392F79873D131CA5EABCA6"
//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Automation;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Controls.Ribbon;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Markup;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Media.Effects;
using System.Windows.Media.Imaging;
using System.Windows.Media.Media3D;
using System.Windows.Media.TextFormatting;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Windows.Shell;


namespace GestorGUI.GUI {
    
    
    /// <summary>
    /// Main
    /// </summary>
    public partial class Main : System.Windows.Window, System.Windows.Markup.IComponentConnector {
        
        
        #line 10 "..\..\..\..\GUI\Main.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button BotonSalir;
        
        #line default
        #line hidden
        
        
        #line 11 "..\..\..\..\GUI\Main.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button BotonListado;
        
        #line default
        #line hidden
        
        
        #line 12 "..\..\..\..\GUI\Main.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button BotonSolDev;
        
        #line default
        #line hidden
        
        
        #line 13 "..\..\..\..\GUI\Main.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button BotonAñadir;
        
        #line default
        #line hidden
        
        
        #line 14 "..\..\..\..\GUI\Main.xaml"
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1823:AvoidUnusedPrivateFields")]
        internal System.Windows.Controls.Button BotonEliminar;
        
        #line default
        #line hidden
        
        private bool _contentLoaded;
        
        /// <summary>
        /// InitializeComponent
        /// </summary>
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "5.0.13.0")]
        public void InitializeComponent() {
            if (_contentLoaded) {
                return;
            }
            _contentLoaded = true;
            System.Uri resourceLocater = new System.Uri("/GestorGUI;component/gui/main.xaml", System.UriKind.Relative);
            
            #line 1 "..\..\..\..\GUI\Main.xaml"
            System.Windows.Application.LoadComponent(this, resourceLocater);
            
            #line default
            #line hidden
        }
        
        [System.Diagnostics.DebuggerNonUserCodeAttribute()]
        [System.CodeDom.Compiler.GeneratedCodeAttribute("PresentationBuildTasks", "5.0.13.0")]
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Never)]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Design", "CA1033:InterfaceMethodsShouldBeCallableByChildTypes")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Maintainability", "CA1502:AvoidExcessiveComplexity")]
        [System.Diagnostics.CodeAnalysis.SuppressMessageAttribute("Microsoft.Performance", "CA1800:DoNotCastUnnecessarily")]
        void System.Windows.Markup.IComponentConnector.Connect(int connectionId, object target) {
            switch (connectionId)
            {
            case 1:
            this.BotonSalir = ((System.Windows.Controls.Button)(target));
            
            #line 10 "..\..\..\..\GUI\Main.xaml"
            this.BotonSalir.Click += new System.Windows.RoutedEventHandler(this.BotonSalir_OnClick);
            
            #line default
            #line hidden
            return;
            case 2:
            this.BotonListado = ((System.Windows.Controls.Button)(target));
            
            #line 11 "..\..\..\..\GUI\Main.xaml"
            this.BotonListado.Click += new System.Windows.RoutedEventHandler(this.BotonListado_OnClick);
            
            #line default
            #line hidden
            return;
            case 3:
            this.BotonSolDev = ((System.Windows.Controls.Button)(target));
            
            #line 12 "..\..\..\..\GUI\Main.xaml"
            this.BotonSolDev.Click += new System.Windows.RoutedEventHandler(this.BotonSolDev_OnClick);
            
            #line default
            #line hidden
            return;
            case 4:
            this.BotonAñadir = ((System.Windows.Controls.Button)(target));
            
            #line 13 "..\..\..\..\GUI\Main.xaml"
            this.BotonAñadir.Click += new System.Windows.RoutedEventHandler(this.BotonAñadir_OnClick);
            
            #line default
            #line hidden
            return;
            case 5:
            this.BotonEliminar = ((System.Windows.Controls.Button)(target));
            
            #line 14 "..\..\..\..\GUI\Main.xaml"
            this.BotonEliminar.Click += new System.Windows.RoutedEventHandler(this.BotonEliminar_OnClick);
            
            #line default
            #line hidden
            return;
            }
            this._contentLoaded = true;
        }
    }
}

